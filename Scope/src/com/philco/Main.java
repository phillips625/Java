package com.philco;

public class Main {

    public static void main(String[] args) {

        // This 'varThree' String is different from that declared in the 'ScopeCheck' class.
        String varFour = "This is private to Main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        scopeInstance.useInner();
        System.out.println("scopeInstance varOne is " + scopeInstance.getVarOne());
        System.out.println(varFour);

        scopeInstance.timesTwo();


        System.out.println("******************************");

        // Declaring inner class.
        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();
    }
}
