package com.devx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static  org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class NumberTest {

    private static final Object[] getCharacs(){
        return $(
          $("12345"),
          $("-38485"),
          $("0"),
          $("-99999999999999999999999999999999999999999999999999999999999999999999999")
        );
    }

    private static final Object[] getWrongNumber(){
        return $(
            $("123a5"),
            $("a94614917644379"),
            $("123912639127373872361923.9"),
            $("123-22")
        );
    }

    private static final Object[] getNumbers(){
        return $(
            $(2,5),
            $(5,2),
            $(-2,3),
            $(15,4),
            $(0,0),
            $(-2,9),
            $(-9,2),
            $(2,-7),
            $(7,-2),
            $(19,1),
            $(5,5),
            $(-2, 30),
            $(30, -2),
            $(-2,-7),
            $(-7,-2),
            $(3,3),
            $(-7,-7),
            $(15,4),
            $(6,3000),
            $(3221, 6),
            $(-3333, -1),
            $(-333,1),
            $(33333, -21),
            $(100216, 12),
            $(15,32),
            $(32,15),
            $(333,118),
            $(333,332),
            $(-333, -332),
            $(333,333),
            $(-333,-333)
        );
    }

    private static final Object[] getNumbersPositive(){
        return $(
                $(2,5),
                $(5,2),
                $(2,3),
                $(15,4),
                $(0,0),
                $(2,9),
                $(9,2),
                $(2,7),
                $(7,2),
                $(19,1),
                $(5,5),
                $(2, 30),
                $(30, 2),
                $(2,7),
                $(7,2),
                $(3,3),
                $(7,7),
                $(1550,400),
                $(6,3000),
                $(3221, 6),
                $(3333, 1),
                $(333,1),
                $(33333, 21),
                $(100216, 12),
                $(15,32),
                $(32,15)
        );
    }

    public static final Object[] getNumAndPower(){
        return $(
                $(2,5),
                $(5,2),
                $(2,3),
                $(15,4),
                $(-3,2),
                $(-3,3)
        );
    }

    @Test
    @Parameters(method = "getNumAndPower")
    public void operationPower(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setShortStrategy(new NumberPower());
        int exp = (int)Math.pow(a,b);
        int res = Integer.parseInt(ctx.executeOperationWithShortStrategy(new Number(a),b).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbers")
    public void operationMinus(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setStrategy(new NumberSubstract());
        int exp = a-b;
        int res = Integer.parseInt(ctx.executeStrategy(new Number(a),new Number(b)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method = "getNumbers")
    public void operationAddModulus(int a, int b){
        NumberContext ctx = new NumberContext();
        NumberContext ctxMod = new NumberContext();
        ctx.setStrategy(new NumberAdd());
        ctxMod.setStrategy(new NumberMod());
        Number t = ctx.executeStrategy(new Number(a),new Number(b));
        int exp = (a+b)%2;
        int res = Integer.parseInt(ctxMod.executeStrategy(t, new Number(2)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method = "getNumbers")
    public void operationSubstrModulus(int a, int b){
        NumberContext ctx = new NumberContext();
        NumberContext ctxMod = new NumberContext();
        ctx.setStrategy(new NumberSubstract());
        ctxMod.setStrategy(new NumberMod());
        Number t = ctx.executeStrategy(new Number(a),new Number(b));
        int exp = (a-b)%2;
        int res = Integer.parseInt(ctxMod.executeStrategy(t, new Number(2)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }


    @Test
    @Parameters(method="getNumbers")
    public void operationLessComparison(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setBooleanStrategy(new NumberLessComparison());
        boolean exp = a<b;
        boolean res = ctx.executeBooleanStrategy(new Number(a),new Number(b));
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbers")
    public void operationGreaterComparison(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setBooleanStrategy(new NumberGreaterComparison());
        boolean exp = a>b;
        boolean res = ctx.executeBooleanStrategy(new Number(a),new Number(b));
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbers")
    public void operationLessOrEqualComparison(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setBooleanStrategy(new NumberLessOrEqualComparison());
        boolean exp = a<=b;
        boolean res = ctx.executeBooleanStrategy(new Number(a),new Number(b));
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbers")
    public void operationMult(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setStrategy(new NumberMultiplication());
        int exp = a*b;
        int res = Integer.parseInt(ctx.executeStrategy(new Number(a),new Number(b)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbersPositive")
    public void operationMod(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setStrategy(new NumberMod());
        int exp = a%b;
        int res = Integer.parseInt(ctx.executeStrategy(new Number(a),new Number(b)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getNumbersPositive")
    public void operationDiv(int a, int b){
        NumberContext ctx = new NumberContext();
        ctx.setStrategy(new NumberDivision());
        int exp = a/b;
        int res = Integer.parseInt(ctx.executeStrategy(new Number(a),new Number(b)).toString());
        assertTrue("Expected "+exp+" got "+res,exp==res);
    }

    @Test
    @Parameters(method="getCharacs")
    public void characterIsTrue(String n){
        Number number = new Number(n);
        assertEquals(true, number.isNumber(n));
    }



    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getWrongNumber")
    public void characterIsFalse(String n){
        Number number = new Number(n);
        assertFalse(number.isNumber(n));
    }
}
