package com.devx;

public class App 
{
    public static void main( String[] args )
    {
        //Just for testing sake. TODO:get rid of it
        NumberContext context = new NumberContext();
        context.setStrategy(new NumberMultiplication());
        Number res = context.executeStrategy(new Number("20"), new Number("0"));

    }
}
