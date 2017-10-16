package com.accelad.math.evaluator.core.evaluator.diff;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

class VariableCheckFunctionDecorator<X extends Field<X>> extends DifferentialFunction<X> {

    private final DifferentialFunction<X> function;
    private final Map<DifferentialFunction<X>, Set<DifferentialFunctionPredicateChecker>> variableCheckerMap;

    VariableCheckFunctionDecorator(DifferentialFunction<X> function) {
        this(function, new HashMap<>());
    }

    private VariableCheckFunctionDecorator(DifferentialFunction<X> function,
            Map<DifferentialFunction<X>, Set<DifferentialFunctionPredicateChecker>> variableCheckerMap) {
        this.function = function;
        this.variableCheckerMap = variableCheckerMap;
    }

    void addArgTest(DifferentialFunction<X> argFunction,
            Predicate<DifferentialFunction<X>> predicate, String errorMessage) {
        variableCheckerMap.putIfAbsent(argFunction, new HashSet<>());
        variableCheckerMap.get(argFunction)
                          .add(new DifferentialFunctionPredicateChecker(predicate, errorMessage));
    }

    @Override
    public X getValue() {
        checkVariables();
        return function.getValue();
    }

    @Override
    public double getReal() {
        checkVariables();
        return function.getReal();
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {
        return function.getFormula(variables);
    }

    @Override
    public String toString() {
        return function.toString();
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> v) {
        return new VariableCheckFunctionDecorator<>(function.diff(v), variableCheckerMap);
    }

    private void checkVariables() {
        variableCheckerMap.forEach(
                (v, checkers) -> checkers.forEach(checker -> checker.checkVariable(v)));
    }

    private class DifferentialFunctionPredicateChecker {
        private final Predicate<DifferentialFunction<X>> predicate;
        private final String error;

        private DifferentialFunctionPredicateChecker(Predicate<DifferentialFunction<X>> predicate,
                String error) {
            this.predicate = predicate;
            this.error = error;
        }

        public void checkVariable(DifferentialFunction<X> variable) {
            if (!predicate.test(variable)) {
                throw new IllegalArgumentException(error);
            }
        }
    }
}
