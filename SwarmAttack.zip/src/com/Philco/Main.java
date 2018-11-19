package com.Philco;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BeeHive firstBeehive = new BeeHive(1, 20, 15);
        BeeHive secondBeehive = new BeeHive(1, 20, 15);

        do {

            Scanner reader = new Scanner(System.in);

            System.out.println("Please enter \"TICK\" to attack!");
            String attack = reader.next().toString();

            if (attack == "tick") {


            }


        }

        while(firstBeehive.queen()[0] != 0 || secondBeehive.queen()[0] != 0 );

        if(firstBeehive.queen()[0] == 0) {
            System.out.println("Second Beehive Wins!");
        }
        else {
            System.out.println("First Beehive Wins!");
        }
    }
}

