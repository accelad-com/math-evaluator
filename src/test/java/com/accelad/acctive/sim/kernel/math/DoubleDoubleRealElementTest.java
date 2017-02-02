package com.accelad.acctive.sim.kernel.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.accelad.math.doubledouble.DoubleDouble;
import com.accelad.math.nilgiri.DoubleDoubleReal;

public class DoubleDoubleRealElementTest {

    @Test
    public void should_return_origin_double_double_when_get_DoubleDouble() {
        DoubleDouble veryBig = DoubleDouble.ONE.multiply(DoubleDouble.valueOf("10")).pow(32);
        DoubleDouble veryLow = DoubleDouble.ONE.multiply(DoubleDouble.valueOf("10")).pow(-32);
        DoubleDouble doubleDouble = veryBig.add(veryLow);
        DoubleDoubleReal doubleDoubleReal = new DoubleDoubleReal(doubleDouble);
        DoubleDoubleRealElement doubleDoubleRealElement = new DoubleDoubleRealElement(
                doubleDoubleReal);

        DoubleDouble value = doubleDoubleRealElement.doubleDoubleValue();

        assertEquals(value, doubleDouble);
        assertThat(value, is(doubleDouble));
    }
}