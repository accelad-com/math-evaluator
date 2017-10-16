package com.accelad.math.evaluator.math;

import com.accelad.math.nilgiri.RealNumber;

public class PointRealNumber<X extends RealNumber<X>> {
    private X x;
    private X y;

    public PointRealNumber() {
        // we can create an empty point
    }

    public PointRealNumber(X x, X y) {
        this.x = x;
        this.y = y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(X y) {
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public X getY() {
        return y;
    }

    @Override
    public String toString() {
        return "PointRealNumber [x=" + x + ", y=" + y + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PointRealNumber other = (PointRealNumber) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            if (other.y != null)
                return false;
        } else if (!y.equals(other.y))
            return false;
        return true;
    }

}
