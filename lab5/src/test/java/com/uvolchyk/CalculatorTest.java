package com.uvolchyk;

import com.uvolchyk.entity.Calculator;
import com.uvolchyk.exception.CalculatorException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testCalculatorThrowsExceptionDenominatorGreaterThanOne() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.sumOfDecreasingGeometricSeries(0.33, 2.0));
    }

    @Test
    public void testCalculatorReturnsValidSumForSeries() throws CalculatorException {
        Double expectedResult = 0.25;
        Assert.assertEquals(calculator.sumOfDecreasingGeometricSeries(0.2, 0.2), expectedResult);
    }

    @Test
    public void testCalculatorReturnsValidSumForNegativeMembers() throws CalculatorException {
        Double expectedResult = -0.3333333333333333;
        Assert.assertEquals(calculator.sumOfDecreasingGeometricSeries(-0.5, -0.5), expectedResult);
    }

    @Test
    public void testDenominatorIsInfiniteException() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.sumOfDecreasingGeometricSeries(0.6, Double.MAX_VALUE));
    }

    @Test
    public void testDiscriminantLessThanZeroException() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.realRootsOfSquareEquation(4.0, 1.0, 2.0));
    }

    @Test
    public void testDiscriminantEqualsZero() throws CalculatorException {
        List<Double> expectedResult = Lists.newArrayList(1.0, 1.0);
        Assert.assertEquals(calculator.realRootsOfSquareEquation(1.0, -2.0, 1.0), expectedResult);
    }

    @Test
    public void testDiscriminantGreaterThanZero() throws CalculatorException {
        List<Double> expectedResult = Lists.newArrayList(-1.0, -3.0);
        Assert.assertEquals(calculator.realRootsOfSquareEquation(1.0, 4.0, 3.0), expectedResult);
    }

    @Test
    public void testCoefficientIsInfinite() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.realRootsOfSquareEquation(Double.MAX_VALUE, 5.0, 1.5));
    }

    @Test
    public void testCalculatorReturnsValidCircleSquare() throws CalculatorException {
        Double expectedResult = 78.53981633974483;
        Assert.assertEquals(calculator.circleSquare(5.0), expectedResult);
    }

    @Test
    public void testRadiusIsNegativeException() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.circleSquare(-123.0));
    }

    @Test
    public void testRadiusIsZeroException() {
        Assert.assertThrows(CalculatorException.class, () -> calculator.circleSquare(0.0));
    }

    @AfterMethod
    public void tearDown() {
        calculator = null;
    }
}
