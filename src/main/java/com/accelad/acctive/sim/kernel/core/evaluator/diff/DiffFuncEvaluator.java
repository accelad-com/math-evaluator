package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import static com.accelad.acctive.sim.kernel.core.evaluator.diff.FunctionKind.fromFunction;
import static com.accelad.acctive.sim.kernel.core.evaluator.diff.OperatorKind.fromOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.acctive.sim.kernel.core.evaluator.OutsideFunction;
import com.accelad.acctive.sim.kernel.core.evaluator.Point;
import com.accelad.acctive.sim.kernel.core.evaluator.TableFunction;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.Constant;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.fathzer.soft.javaluator.AbstractEvaluator;
import com.fathzer.soft.javaluator.BracketPair;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Parameters;

/**
 * Evaluates string expression and return a autodiff formula
 *
 * @author Yoann
 */
public class DiffFuncEvaluator<X extends Field<X>>
        extends AbstractEvaluator<DifferentialFunction<X>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiffFuncEvaluator.class);
    private static final Parameters PARAMETERS;

    static {
        PARAMETERS = new Parameters();
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
        PARAMETERS.addFunctionBracket(BracketPair.PARENTHESES);
        Arrays.stream(OperatorKind.values()).map(OperatorKind::getOperator)
                .forEach(PARAMETERS::add);
        Arrays.stream(FunctionKind.values()).map(FunctionKind::getFunction)
                .forEach(PARAMETERS::add);
    }

    protected final MathFactory<X> mathFactory;

    private final Map<String, Variable<X>> variables;
    private final Map<String, Constant<X>> constants;
    private final Map<String, DifferentialFunction<X>> functions = new HashMap<>();

    public DiffFuncEvaluator(MathFactory<X> mathFactory, Map<String, Variable<X>> variables,
            Map<String, Constant<X>> constants) {
        super(PARAMETERS);
        this.mathFactory = mathFactory;
        this.variables = variables;
        this.constants = constants;
    }

    public DiffFuncEvaluator(MathFactory<X> mathFactory) {
        this(mathFactory, new HashMap<>(), new HashMap<>());
    }

    public Map<String, Variable<X>> getVariables() {
        return variables;
    }

    public void addVariable(Variable<X> var) {
        if (variables.containsKey(var.getName())) {
            LOGGER.warn("A variable with same name has already been registered : {}",
                    var.getName());
        }
        variables.put(var.getName(), var);
    }

    public void addConstant(String name, Constant<X> c) {
        if (constants.containsKey(name)) {
            LOGGER.warn("A constant with same name has already been registered : {}", name);
        }
        constants.put(name, c);
    }

    public void addFunction(String name, DifferentialFunction<X> func) {
        if (functions.containsKey(name)) {
            LOGGER.warn("A function with same name has already been registered : {}", name);
        }
        functions.put(name, func);
    }

    @Override
    protected DifferentialFunction<X> toValue(String literal, Object context) {

        Constant<X> constant = constants.get(literal);
        if (constant != null) {
            return constant;
        }

        Variable<X> variable = variables.get(literal);
        if (variable != null) {
            return variable;
        }

        DifferentialFunction<X> function = functions.get(literal);
        if (function != null) {
            return function;
        }

        double doubleValue;
        try {
            doubleValue = Double.parseDouble(literal);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Missing variable or constant Definition : " + literal,
                    e);
        }
        return mathFactory.val(doubleValue);
    }

    @Override
    protected DifferentialFunction<X> evaluate(Function function,
            Iterator<DifferentialFunction<X>> arguments, Object evaluationContext) {
        return fromFunction(function)
                .map(functionKind -> buildFunction(functionKind, toList(arguments)))
                .orElseGet(() -> super.evaluate(function, arguments, evaluationContext));
    }

    @Override
    protected DifferentialFunction<X> evaluate(Operator operator,
            Iterator<DifferentialFunction<X>> operands, Object evaluationContext) {
        return fromOperator(operator)
                .map(operatorKind -> buildOperator(operatorKind, toList(operands)))
                .orElseGet(() -> super.evaluate(operator, operands, evaluationContext));
    }

    private DifferentialFunction<X> buildFunction(FunctionKind functionKind,
            List<DifferentialFunction<X>> args) {
        return buildDifferentialFunction(getFunctionProvider(functionKind), args);
    }

    private DifferentialFunction<X> buildOperator(OperatorKind operatorKind,
            List<DifferentialFunction<X>> args) {
        return buildDifferentialFunction(getFunctionProvider(operatorKind), args);
    }

    private DifferentialFunction<X> buildDifferentialFunction(
            DifferentialFunctionProvider<X> differentialFunctionProvider,
            List<DifferentialFunction<X>> args) {
        return differentialFunctionProvider.apply(mathFactory, args);
    }

    private DifferentialFunctionProvider<X> getFunctionProvider(FunctionKind functionKind) {
        return (factory, args) -> getFunction(functionKind, factory, args);
    }

    private DifferentialFunctionProvider<X> getFunctionProvider(OperatorKind operatorKind) {
        return (factory, args) -> getFunction(operatorKind, factory, args);
    }

    private DifferentialFunction<X> getFunction(FunctionKind functionKind, MathFactory<X> factory,
            List<DifferentialFunction<X>> args) {
        switch (functionKind) {
        case GREATER_THAN:
            return new GreaterThanFunction<>(factory, args.get(0), args.get(1));
        case STRICTLY_LESS_THAN:
            return new StrictlyLessThanFunction<>(factory, args.get(0), args.get(1));
        case BETWEEN_STRICTLY_LESS_THAN_MAX:
            return new BetweenStrictlyLessThanMaxFunction<>(factory, args.get(0), args.get(1), args.get(2));
        case OUTSIDE:
            return new OutsideFunction<>(factory, args.get(0), args.get(1), args.get(2));
        case LATCH:
            return new LatchFunction<>(factory, args.get(0), args.get(1), args.get(2));
        case TABLE:
            return tableFunction(factory, args);
        case ABS:
            return factory.abs(args.get(0));
        case FLOOR:
            return factory.floor(args.get(0));
        case MAX:
            return factory.max(args.get(0), args.get(1));
        case MIN:
            return factory.min(args.get(0), args.get(1));
        case COSINE:
            return factory.cos(args.get(0));
        case SINE:
            return factory.sin(args.get(0));
        case TANGENT:
            return factory.tan(args.get(0));
        case ARC_COSINE:
            return factory.acos(args.get(0));
        case ARC_SINE:
            return factory.asin(args.get(0));
        case ARC_TANGENT:
            return factory.atan(args.get(0));
        case HYPERBOLIC_COSINE:
            return factory.cosh(args.get(0));
        case HYPERBOLIC_SINE:
            return factory.sinh(args.get(0));
        case HYPERBOLIC_TANGENT:
            return factory.tanh(args.get(0));
        case ARC_HYPERBOLIC_COSINE:
            return factory.acosh(args.get(0));
        case ARC_HYPERBOLIC_SINE:
            return factory.asinh(args.get(0));
        case ARC_HYPERBOLIC_TANGENT:
            return factory.atanh(args.get(0));
        default:
            throw new IllegalArgumentException(
                    String.format("Unsupported FunctionKind: %s", functionKind));
        }
    }

    private DifferentialFunction<X> getFunction(OperatorKind operatorKind, MathFactory<X> factory,
            List<DifferentialFunction<X>> args) {
        switch (operatorKind) {
        case PLUS:
            return args.get(0).plus(args.get(1));
        case MINUS:
            return args.get(0).minus(args.get(1));
        case MUL:
            return args.get(0).mul(args.get(1));
        case DIV:
            return args.get(0).div(args.get(1));
        case NEGATE:
            return args.get(0).negate();
        case EXP:
            return factory.exp(args.get(0));
        case EXPL:
            return limitedExponential(factory, args);
        case DIFF:
            return args.get(0).diff((Variable<X>) args.get(1));
        case EXPONENT:
            return factory.pow(args.get(0), (Constant<X>) args.get(1));
        case ENG:
            return engFunction(factory, args);
        default:
            throw new IllegalArgumentException(
                    String.format("Unsupported OperatorKind: %s", operatorKind));
        }
    }

    private DifferentialFunction<X> limitedExponential(MathFactory<X> mathFactory,
            List<DifferentialFunction<X>> args) {
        DifferentialFunction<X> x = args.get(0);
        DifferentialFunction<X> x0 = args.get(1);
        DifferentialFunction<X> lowPart = new StrictlyLessThanFunction<>(mathFactory, x,
                mathFactory.one().minus(x0)).mul(
                x.plus(x0).mul(mathFactory.exp(mathFactory.one().minus(x0))));
        DifferentialFunction<X> middlePart = new RestrictedDomainFunction<>(mathFactory, x,
                mathFactory.exp(x), mathFactory.one().minus(x0), x0);
        DifferentialFunction<X> highPart = new GreaterThanFunction<>(mathFactory, x, x0).mul(
                x.minus(x0).plus(mathFactory.one()).mul(mathFactory.exp(x0)));

        VariableCheckFunctionDecorator<X> secureFunction = new VariableCheckFunctionDecorator<>(
                lowPart.plus(middlePart).plus(highPart));
        secureFunction.addArgTest(x0, variable -> variable.getReal() > 0.5,
                "expl(x, x0): x0 must be greater than 0.5 [(1-x0) < x0]");
        return secureFunction;
    }

    private DifferentialFunction<X> tableFunction(MathFactory<X> mathFactory,
            List<DifferentialFunction<X>> args) {
        List<Point<X>> list = new ArrayList<>();
        int count = (int) args.get(1).getReal();
        for (int i = 2; i < count; i++) {
            DifferentialFunction<X> xFunction = args.get(i);
            DifferentialFunction<X> yFunction = args.get(i + 1);
            Point<X> point = new Point<>(xFunction, yFunction);
            list.add(point);
        }
        return new TableFunction<>(mathFactory, args.get(0), list);
    }

    private DifferentialFunction<X> engFunction(MathFactory<X> mathFactory,
            List<DifferentialFunction<X>> args) {
        double val = -args.get(1).getValue().getReal();
        val = Math.pow(10, val);
        return args.get(0).mul(mathFactory.val(val));
    }

    private static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }
}
