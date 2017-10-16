package com.accelad.math.evaluator.core.evaluator.diff;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.accelad.math.evaluator.math.DoubleFactory;
import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.DoubleReal;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;

public class LatchFunctionTest {

    @Test
        public void testGetRealValue() throws Exception {
            MathFactory<DoubleReal> factory = new DoubleFactory();
    
            Variable<DoubleReal> currentTime = factory.var("currenttime", 0);
            DifferentialFunction<DoubleReal> timeTrigger = factory.var("tigger", 5);
            DifferentialFunction<DoubleReal> func = currentTime;
    
            LatchFunction<DoubleReal> mf = new LatchFunction<>(factory, currentTime, timeTrigger,
                    func);
    
            for (double i = 0d; i < 10; i++) {
                currentTime.set(factory.get(i));
    
                if (i < 5) {
                    assertEquals(i, mf.getReal(), 1e-12);
                } else {
                    assertEquals(5, mf.getReal(), 1e-12);
                }
            }
            }
}
