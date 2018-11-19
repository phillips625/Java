package com.Philco;

public class Main {

    public static void main(String[] args) {

        Account bobsAccount = new Account();//"124432", 124.32, "Bob James", "aager@yahoo.com", "+254 424 4344");

        bobsAccount.getBalance();
        /////////// A CONSTRUCTOR allows you to set all these in one go
//        bobsAccount.setCustomerName("Bob James");
//        bobsAccount.setBalance(124.32);
//        bobsAccount.setNumber("124432");
//        bobsAccount.setCustomerEmailAddress("aager@yahoo.com");
//        bobsAccount.setCustomerPhoneNumber("+254 424 4344");

        bobsAccount.withdrawal(100.00);

        bobsAccount.deposit(50.0);
        bobsAccount.withdrawal(100.00);

        // Since we now have 101.0 deposited, we should now get a value that we now have more money than we want to withdraw.
        bobsAccount.deposit(51.0);
        bobsAccount.withdrawal(100.00);

        System.out.println("----------------------");

        Account timsAccount = new Account("Tim", "tim@gmail.com", "12345");
        System.out.println(timsAccount.getBalance());

        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");

        VipPerson Me1 = new VipPerson("Phh", 123.34);
        System.out.println(Me1.getName());

        VipPerson Me2 = new VipPerson();
        System.out.println(Me2.getName());

        VipPerson Me3 = new VipPerson("Phhop", 234.34, "uih@yahoo");
        System.out.println(Me3.getName());


    }
}
