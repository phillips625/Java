package com.Philco;

/**
 * Created by PhillipsDaramola on 04/10/2017.
 */

// CREATING OUR OWN LINKED LIST.
public abstract class ListItem {
    // These variables are protected rather than private because we want our concrete subclasses (aka classes will be
    // inheriting from this class) to be able to have access to them.
    // Creating instances of the very same class in the same class.
    // This will hold links to the previous and next items that we'll be saving.
    protected ListItem rightLink = null;
    protected ListItem leftLink = null;

    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    // Returns the next item
    abstract ListItem next();
    // Sets the next item
    abstract ListItem setNext(ListItem item);
    // Returns the previous item
    abstract ListItem previous();
    // Sets the previous item
    abstract ListItem setPrevious(ListItem item);
    // Compares two items.
    abstract int compareTo(ListItem item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
