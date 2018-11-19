package com.Philco;

// We've been using packages all along - even for the most basic use (int, double, string, etc). The basic primitive type we
// use all come from 'import java.lang' package.
// import java.lang.Integer

import javafx.scene.Node;
// If you include this import with the same class name (Node) at above, as Intellij is unsure which import to use.
// import org.w3c.dom.Node;

/// Package - Used to group related classes and interfaces together.
public class Main {

    public static void main(String[] args) {

                        /*This would work as one class is obtained from an imported package
                         * and the other is manually specified (anotherNode).*/

                // To open options of classes (with the name 'Node') available to you.
        // Highlight 'Node', Press alt + enter.
        // The I select 'javafx.scene' and 'import javafx.scene.Node;' is imported.

        // Node node = null;

                    // OR
        // We can specify the package that contains the Node class we want to work with.
        Node node = null;

        // N.B - You can't import the same class with two different packages.
        org.w3c.dom.Node anotherNode = null;
    }
}


/*
*           This wouldn't work as you're trying to import the same class Name from different packages
*
* import javafx.scene.Node;
import org.w3c.dom.Node;

/// Package - Used to group related classes and interfaces together.
public class Main {

    public static void main(String[] args) {
                // To open options of classes (with the name 'Node') available to you.
        // Highlight 'Node', Press alt + enter.
        // The I select 'javafx.scene' and 'import javafx.scene.Node;' is imported.

        // Node node = null;

                    // OR
        // We can specify the package that contains the Node class we want to work with.
        javafx.scene.Node node = null;

        // N.B - You can't import the same class with two different packages.
        org.w3c.dom.Node anotherNode = null;
    }
}
*
* */