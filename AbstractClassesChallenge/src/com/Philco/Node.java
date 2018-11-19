package com.Philco;

/**
 * Created by PhillipsDaramola on 05/10/2017.
 */
public class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        // This is a field in the ListItem abstract class.
        return this.rightLink;
    }

    // setNext returns the right link (when it normally shouldn't as it's a setter) because it can help to shortcut traversing the
    // linked list.
    @Override
    ListItem setNext(ListItem item) {
        this.rightLink = item;
        return this.rightLink;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem item) {
        this.leftLink = item;
        return this.leftLink;
    }

    @Override
    int compareTo(ListItem item) {

        if (item != null){
            // Comparing two strings - we can do this because the super getValue's method returns an 'Object'.
            // String class already has it's own 'compareTo' method. 'compareTo' method will return 0,1 or -1 depending on the value
            // you pass in.
            return ((String) super.getValue()).compareTo((String) item.getValue());
        }
        else {
            return -1;
        }
    }
}
