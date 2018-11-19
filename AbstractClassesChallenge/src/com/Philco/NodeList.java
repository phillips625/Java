package com.Philco;

/**
 * Created by PhillipsDaramola on 05/10/2017.
 */
public interface NodeList {
    // This is the starting node.
    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    // A way of going through the list starting from the starting node.
    void traverse(ListItem root);
}
