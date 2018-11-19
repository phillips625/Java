package com.example.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhillipsDaramola on 29/09/2017.
 */

            // Interface vs Inheritance
    // Interface ensures that these methods are implemented and that any implementations by the classes
    // doesn't have to tickle down to the super class (like in inheritance) - every class have complete autonomy on
    // how they implement these methods.
public interface ISaveable {

    // Methods that must be implemented by any class implementing this interface.

//    ArrayList<String> write();
//    void read(ArrayList<String> savedValues);

    // OR - Use 'list' for maximum flexibility - so in the future we can use it with ArrayLists or Linked lists of vectors, etc.
    // Now we can define any kind of list.
    List<String> write();
    void read(List<String> savedValues);
}
