package com.accelad.acctive.sim.kernel.core.evaluator;

import com.accelad.acctive.sim.kernel.core.evaluator.diff.DiffFuncEvaluator;
import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.DoubleDoubleComplex;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;

public class ComplexDiffFuncEvaluator extends DiffFuncEvaluator<DoubleDoubleComplex> {

    private static final String PHYSIC_IMAGINARY = "j";
    private static final String MATH_IMAGINARY = "i";

    public ComplexDiffFuncEvaluator(MathFactory<DoubleDoubleComplex> dfFactory) {
        super(dfFactory);
    }

    @Override
    protected DifferentialFunction<DoubleDoubleComplex> toValue(String literal, Object context) {
        String lowercaseLiteral = literal.toLowerCase();
        if (lowercaseLiteral.equals(MATH_IMAGINARY) || lowercaseLiteral.equals(PHYSIC_IMAGINARY)) {
            return mathFactory.val(new DoubleDoubleComplex(0, 1));
        }
        return super.toValue(literal, context);
    }
}
