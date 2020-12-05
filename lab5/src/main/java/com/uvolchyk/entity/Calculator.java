package com.uvolchyk.entity;

import com.uvolchyk.exception.CalculatorException;

import java.util.List;

public class Calculator {

    public Double sumOfDecreasingGeometricSeries(Double firstMember, Double denominator) throws CalculatorException {
        if (Double.isInfinite(firstMember) || Double.isInfinite(denominator)) {
            throw new CalculatorException("Arguments should be finite");
        }
        if (denominator >= 1) {
            throw new CalculatorException("Denominator must be absolutely less than 1");
        }
        return firstMember / (1 - denominator);
    }

    public List<Double> realRootsOfSquareEquation(Double a, Double b, Double c) throws CalculatorException {
        if (Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
            throw new CalculatorException("Arguments should be finite");
        }
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d < 0) {
            throw new CalculatorException("Equation has no real roots");
        }
        return List.of((-b + Math.sqrt(d)) / (2 * a), (-b - Math.sqrt(d)) / (2 * a));
    }

    public Double circleSquare(Double r) throws CalculatorException {
        if (Double.isInfinite(r)) {
            throw new CalculatorException("Argument should be finite");
        }
        if (r <= 0) {
            throw new CalculatorException("Radius must be positive");
        }
        return Math.PI * Math.pow(r, 2);
    }


}
