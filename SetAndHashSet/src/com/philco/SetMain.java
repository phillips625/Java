package com.philco;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by PhillipsDaramola on 30/10/2017.
 */


                        // Asymmetric and Symmetric difference - Set
// A set is useful to collect all the unique items in a collection.

// On google/java docs, search 'Set Interface'.

public class SetMain {

    public static void main(String[] args) {

        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();

        for (int i = 1; i <= 100 ; i++){

            squares.add(i * i);
            cubes.add(i * i * i);
        }

        System.out.println("square size: " +  squares.size() + ". " + "cubes size: " + cubes.size());

        // Initializing the 'union' set with the 100 'squares' values. union Hashset doesn't affect the original 'squares' and 'cubes'.
        Set<Integer> union = new HashSet<>(squares);

        // Using the addAll method to add all the cubes as well
        union.addAll(cubes);

        // Output is 196 instead of 200. This basically means that there were 4 common items in the square and cube hashmaps.
        // Bulk operations like the ones above - 'Set<Integer> union = new HashSet<>(squares);' and
        // 'union.addAll(cubes);' - are destructive.
        System.out.println("union size: " + union.size());

        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println("Intersections size: " + intersection.size());
        for (int i : intersection) {
            System.out.println(i + " is the square of " + Math.sqrt(i) + " and the cube of " + Math.cbrt(i));
        }

        // In Sets, two differences are defined - Asymmetric difference (java has defined a set method to removes elements of one set
        // from another) and Symmetric difference.

        Set<String> words = new HashSet<>();
        String sentence = "one day in the year of the fox";
        String[] arrayWords = sentence.split(" ");
        words.addAll(Arrays.asList(arrayWords));

        for (String s : words) {
            System.out.println(s);
        }

        Set<String> nature = new HashSet<>();
        Set<String> divine = new HashSet<>();

        String[] natureWords = {"all", "nature", "is", "but", "art", "unknown", "to", "thee"};
        // Using the 'Arrays' collection and the asList method in the 'Arrays' collection.
        // Passing 'natureWords' to our 'nature' set. Because addAll method only accepts a parameter of the Collections class,
        // we have to convert the string to a Collections type using the 'Arrays' collection.
        nature.addAll(Arrays.asList(natureWords));

        String[] divineWords = {"to", "err", "is", "human", "to", "forgive", "divine"};
        divine.addAll(Arrays.asList(divineWords));

                // Asymmetric difference - diff1 and diff2 are examples of asymmetric differences.
        System.out.println("nature - divine: ");
        Set<String> diff1 = new HashSet<>(nature);
        // nature TAKE divine - removing elements of 'divine' from 'nature'.
        diff1.removeAll(divine);
        printSet(diff1);

        System.out.println("divine - nature: ");
        Set<String> diff2 = new HashSet<>(divine);
        // Divine TAKE nature - removing elements of 'nature' from 'divine'.
        diff2.removeAll(nature);
        printSet(diff2);

        // Symmetric difference - The union of the two sets (nature and divine) minus the intersection of the 2 sets.
        // It describes the sum of sets contained in one set or the other but not both.
        Set<String> unionTest = new HashSet<>(nature);
        // This is the union of the divine and the nature sets.
        unionTest.addAll(divine);
        Set<String> intersectionTest = new HashSet<>(nature);
        intersectionTest.retainAll(divine);

        System.out.println("Symmetric difference");
        // Removes all the intersecting words, leaving the non-intersecting words in the 'unionTest' set.
        unionTest.removeAll(intersectionTest);
        printSet(unionTest);

        // containsAll is the final bulk operation we're going to be using here.
        // containsAll is used to check if one set is the super set of another. containsAll is non destructive as its only testing membership
        // and it's not modifying the set.
        if (nature.containsAll(divine)){
            System.out.println("divine is a subset of nature");
        }
        if (nature.containsAll(intersectionTest)){
            System.out.println("intersectionTest is a subset of nature");
        }
        if (divine.containsAll(intersectionTest)){
            System.out.println("intersectionTest is a subset of divine");
        }
    }

    private static void printSet(Set<String> set){
        System.out.print("\t");
        for (String s : set) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
