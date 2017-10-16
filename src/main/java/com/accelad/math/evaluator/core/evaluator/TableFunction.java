package com.accelad.math.evaluator.core.evaluator;

import java.util.List;
import java.util.stream.Collectors;

import com.accelad.math.evaluator.math.MathFactory;
import com.accelad.math.nilgiri.Field;
import com.accelad.math.nilgiri.autodiff.DifferentialFunction;
import com.accelad.math.nilgiri.autodiff.Variable;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

public class TableFunction<X extends Field<X>>
        extends DifferentialFunction<X> {

    private final MathFactory<X> DFFactory;

    private final DifferentialFunction<X> variable;

    private final RangeMap<Double, Formula<X>> rangeMap;

    public TableFunction(MathFactory<X> DFFactory, DifferentialFunction<X> var,
            List<Point<X>> points) {
        super();
        this.variable = var;
        this.DFFactory = DFFactory;

        rangeMap = TreeRangeMap.create();
        List<Point2> data = points.stream().map(p -> new Point2(p.xFunction, p.yFunction.negate()))
                .collect(Collectors.toList());

        for (int i = 1; i < data.size(); i++) {
            Point2 p1 = data.get(i - 1);
            Point2 p2 = data.get(i);
            rangeMap.put(Range.closedOpen(p1.x.getReal(), p2.x.getReal()),
                    new Formula<X>(p1.x, p1.y, p2.x, p2.y));
        }

    }

    @Override
    public X getValue() {
        double v = variable.getReal();
        return DFFactory.get(rangeMap.get(v).get(variable).getReal());
    }

    @Override
    public double getReal() {
        double v = variable.getValue().getReal();
        Formula<X> f = rangeMap.get(v);
        if (f == null) {
            throw new IllegalStateException("no formula found for " + v);
        }
        return f.get(variable).getReal();
    }


    @Override
    public String toString() {
        return "Between";
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return new DiffFuncWrapper<>(DFFactory, variable, rangeMap, i_v1);
    }

    @Override
    public String getFormula(List<Variable<X>> variables) {

        return null;
    }

    private class Point2 implements Comparable<Point2> {
        DifferentialFunction<X> x;
        DifferentialFunction<X> y;

        public Point2(DifferentialFunction<X> x, DifferentialFunction<X> y) {
            super();
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point2 o) {
            return Double.compare(x.getReal(), o.x.getReal());
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }
}
