package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import java.util.Optional;

import com.fathzer.soft.javaluator.Function;

enum FunctionKind {
    GREATER_THAN(new Function("greaterthan", 2)),
    STRICTLY_LESS_THAN(new Function("lowerthan", 2)),
    BETWEEN_STRICTLY_LESS_THAN_MAX(new Function("between", 3)),
    OUTSIDE(new Function("outside", 3)),
    LATCH(new Function("latch", 3)),
    ABS(new Function("abs", 1)),
    FLOOR(new Function("floor", 1)),
    MAX(new Function("max", 2)),
    MIN(new Function("min", 2)),
    COSINE(new Function("cos", 1)),
    SINE(new Function("sin", 1)),
    TANGENT(new Function("tan", 1)),
    ARC_COSINE(new Function("acos", 1)),
    ARC_SINE(new Function("asin", 1)),
    ARC_TANGENT(new Function("atan", 1)),
    HYPERBOLIC_COSINE(new Function("cosh", 1)),
    HYPERBOLIC_SINE(new Function("sinh", 1)),
    HYPERBOLIC_TANGENT(new Function("tanh", 1)),
    ARC_HYPERBOLIC_COSINE(new Function("acosh", 1)),
    ARC_HYPERBOLIC_SINE(new Function("asinh", 1)),
    ARC_HYPERBOLIC_TANGENT(new Function("atanh", 1));

    private final Function function;

    FunctionKind(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }

    public static Optional<FunctionKind> fromFunction(Function function) {
        for (FunctionKind functionKind : FunctionKind.values()) {
            if (functionKind.function.equals(function)) {
                return Optional.of(functionKind);
            }
        }
        return Optional.empty();
    }
}
