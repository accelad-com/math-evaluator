package com.accelad.math.evaluator.core.evaluator.diff;

import java.util.Optional;

import com.fathzer.soft.javaluator.Operator;
import com.fathzer.soft.javaluator.Operator.Associativity;

enum OperatorKind {
    PLUS(new Operator("+", 2, Associativity.LEFT, 1)),
    MINUS(new Operator("-", 2, Associativity.LEFT, 2)),
    MUL(new Operator("*", 2, Associativity.LEFT, 3)),
    DIV(new Operator("/", 2, Associativity.LEFT, 3)),
    NEGATE(new Operator("-", 1, Associativity.LEFT, 2)),
    EXP(new Operator("exp", 1, Associativity.LEFT, 3)),
    EXPL(new Operator("expl", 2, Associativity.LEFT, 3)),
    DIFF(new Operator("diff", 2, Associativity.LEFT, 3)),
    EXPONENT(new Operator("^", 2, Associativity.LEFT, 4)),
    ENG(new Operator("E-", 2, Associativity.LEFT, 4));

    private final Operator operator;

    OperatorKind(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public static Optional<OperatorKind> fromOperator(Operator operator) {
        for (OperatorKind operatorKind : OperatorKind.values()) {
            if (operatorKind.operator.equals(operator)) {
                return Optional.of(operatorKind);
            }
        }
        return Optional.empty();
    }
}
