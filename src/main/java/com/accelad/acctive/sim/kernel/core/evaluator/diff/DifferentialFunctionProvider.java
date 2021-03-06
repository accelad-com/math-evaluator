package com.accelad.acctive.sim.kernel.core.evaluator.diff;

import java.util.List;
import java.util.function.BiFunction;

import com.accelad.acctive.sim.kernel.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;

@FunctionalInterface
interface DifferentialFunctionProvider<X extends Field<X>>
        extends BiFunction<MathFactory<X>, List<DifferentialFunction<X>>, DifferentialFunction<X>> {

    @Override
    DifferentialFunction<X> apply(MathFactory<X> mathFactory, List<DifferentialFunction<X>> args);
}
