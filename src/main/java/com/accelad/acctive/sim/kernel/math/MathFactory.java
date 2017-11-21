package com.accelad.acctive.sim.kernel.math;

import com.accelad.math.doubledouble.DoubleDouble;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.Constant;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.One;
import com.accelad.math.nilgiri.autodiff.PreEvaluator;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.accelad.math.nilgiri.autodiff.Zero;

public interface MathFactory<X extends Field<X>> {

    X get(X x);

    X get(String literalValue);

    X get(double value);

    X get(DoubleDouble value);

    Constant<X> val(double val);

    Constant<X> val(X i_x);

    Variable<X> var(String i_name);

    Variable<X> var(String i_name, double value);

    Variable<X> var(String i_name, PreEvaluator<X> preEvaluator);

    Variable<X> var(String i_name, double value, PreEvaluator<X> preEvaluator);

    Variable<X> var(String i_name, X i_x, PreEvaluator<X> preEvaluator);

    Variable<X> var(String i_name, X i_x);

    Zero<X> zero();

    One<X> one();

    DifferentialFunction<X> abs(DifferentialFunction<X> i_x);

    DifferentialFunction<X> cos(DifferentialFunction<X> i_x);

    DifferentialFunction<X> sin(DifferentialFunction<X> i_x);

    DifferentialFunction<X> tan(DifferentialFunction<X> i_x);

    DifferentialFunction<X> acos(DifferentialFunction<X> i_x);

    DifferentialFunction<X> asin(DifferentialFunction<X> i_x);

    DifferentialFunction<X> atan(DifferentialFunction<X> i_x);

    DifferentialFunction<X> cosh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> sinh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> tanh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> acosh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> asinh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> atanh(DifferentialFunction<X> i_x);

    DifferentialFunction<X> exp(DifferentialFunction<X> i_x);

    DifferentialFunction<X> log(DifferentialFunction<X> i_x);

    DifferentialFunction<X> pow(DifferentialFunction<X> i_x, Constant<X> i_y);

    DifferentialFunction<X> sqrt(DifferentialFunction<X> i_x);

    DifferentialFunction<X> square(DifferentialFunction<X> i_x);

    DifferentialFunction<X> floor(DifferentialFunction<X> arg1);

    DifferentialFunction<X> max(DifferentialFunction<X> arg1, DifferentialFunction<X> arg2);

    DifferentialFunction<X> min(DifferentialFunction<X> arg1, DifferentialFunction<X> arg2);

}
