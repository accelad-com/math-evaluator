package com.accelad.acctive.sim.kernel.math;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleDoubleRealFieldTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoubleDoubleRealFieldTest.class);

    @Test
    public void test() {

        DoubleDoubleRealElement[][] t = new DoubleDoubleRealElement[][] {
                { new DoubleDoubleRealElement("1.0"), new DoubleDoubleRealElement("2.0") },
                { new DoubleDoubleRealElement("2.0"), new DoubleDoubleRealElement("1.0") }, };
        FieldMatrix<DoubleDoubleRealElement> matrix = new Array2DRowFieldMatrix<>(t);

        FieldLUDecomposition<DoubleDoubleRealElement> lud = new FieldLUDecomposition<>(matrix);

        FieldMatrix<DoubleDoubleRealElement> m = lud.getSolver().getInverse();

        LOGGER.debug("{}", m);
    }

}
