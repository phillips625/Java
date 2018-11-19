package com.philco;

/**
 * Created by PhillipsDaramola on 12/10/2017.
 */
public class ScopeCheck {

    public int publicVar = 0;
    // varOne can be seen by the inner class even though it is declared as a private variable.
    private int varOne = 1;

    public ScopeCheck() {
        System.out.println("Scope check created, varOne =  " + this.varOne + ": publicVar = " + this.publicVar);
    }

    public int getVarOne() {
        return varOne;
    }

    public void timesTwo(){
        // If this is commented out, the 'varOne' in the first for loop will automatically refer to the 'varOne'
        // that is declared in this class.
        // int varOne = 2;

        // Local variable - The 'varOne' here is different from the 'varOne' declared above.
        int varTwo = 2;

        // i's scope is only in the for loop block
        for (int i = 0; i < 10; i++){
            // varOne refers to the local variable.
            System.out.println(i + " times two is " + i * varTwo);
        }
        for (int i = 0; i < 10; i++){
            //  this.varOne refers to the field declared/associate with the class.
            System.out.println(i + " times two is " + i * this.varOne);
        }
        // This will give an error
        // System.out.println(i);
    }

    public void useInner(){

        InnerClass innerClass = new InnerClass();
        System.out.println("varThree from outer class: " + innerClass.varThree);
    }


    // The concept of scope also affects inner classes.
    public class InnerClass {

        public int varThree = 3;

        public InnerClass(){
            System.out.println("Inner Class created, varOne is " + varOne + " and varThree is " + varThree);
        }

        public void timesTwo(){

            System.out.println("varOne is still available here: " + varOne);

            // THis will specifically call the 'timesTwo' in the 'ScopeCheck' class.
            //ScopeCheck.this.timesTwo();

            // If this is commented out then the 'varOne' in the for loop would refer to the one declared in the InnerClass class.
            // int varOne = 2;

            // Local variable - The 'varOne' here is different from the 'varOne' declared above.
            //int privateVar = 2;

            // i's scope is only in the for loop block
            for (int i = 0; i < 10; i++){
                // 'ScopeCheck.this.varOne' ensures that we're selecting the 'varOne' declared in the 'ScopeCheck' class.
                // System.out.println(i + " times two is " + i * ScopeCheck.this.varOne);

                System.out.println(i + " times two is " + i * varThree);

                // varOne refers to the local variable.
                //System.out.println(i + " times two is " + i * varOne);

                // This would give an error if you comment out the declared 'varOne' field.
                // System.out.println(i + " times two is " + i * this.varOne);
            }
        }

    }
}



/*


                    // -----  INITIAL CODE  -------
package com.philco;

public class ScopeCheck {

    public int publicVar = 0;
    private int varOne = 1;

    public ScopeCheck() {
        System.out.println("Scope check created, varOne =  " + this.varOne + ": publicVar = " + this.publicVar);
    }

    public int getVarOne() {
        return varOne;
    }

    public void timesTwo(){
        // If this is commented out, the 'varOne' in the first for loop will automatically refer to the 'varOne'
        // that is declared in this class.
        // int varOne = 2;

        // Local variable - The 'varOne' here is different from the 'varOne' declared above.
        int varOne = 2;

        // i's scope is only in the for loop block
        for (int i = 0; i < 10; i++){
            // varOne refers to the local variable.
            System.out.println(i + " times two is " + i * varOne);
        }
        for (int i = 0; i < 10; i++){
            //  this.varOne refers to the field declared/associate with the class.
            System.out.println(i + " times two is " + i * this.varOne);
        }
        // This will give an error
        // System.out.println(i);
    }

    // The concept of scope also affects inner classes.
    public class InnerClass {

        public int varOne = 3;

        public InnerClass(){
            System.out.println("Inner Class, varOne is " + varOne);
        }

        public void timesTwo(){

            // THis will specifically call the 'timesTwo' in the 'ScopeCheck' class.
            //ScopeCheck.this.timesTwo();

            // If this is commented out then the 'varOne' in the for loop would refer to the one declared in the InnerClass class.
            // int varOne = 2;

            // Local variable - The 'varOne' here is different from the 'varOne' declared above.
            int varOne = 2;

            // i's scope is only in the for loop block
            for (int i = 0; i < 10; i++){
                // 'ScopeCheck.this.varOne' ensures that we're selecting the 'varOne' declared in the 'ScopeCheck' class.
                System.out.println(i + " times two is " + i * ScopeCheck.this.varOne);

                // varOne refers to the local variable.
                //System.out.println(i + " times two is " + i * varOne);

                // This would give an error if you comment out the declared 'varOne' field.
                // System.out.println(i + " times two is " + i * this.varOne);
            }
        }

    }
}






















 */














