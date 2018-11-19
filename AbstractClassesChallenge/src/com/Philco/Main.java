package com.Philco;

public class Main {

    public static void main(String[] args) {

        SearchTree tree = new SearchTree(null);
        tree.traverse(tree.getRoot());

        // Create a string of data array to avoid typing loads of addItems instructions.
        // String stringData = "Darwin Canberra Brisbane Perth Melbourne Canberra Adelaide Sydney";
        String stringData = "7 4 8 9 0 1 3 2 5 6";

        // Splits the various capital cities of Australia.
        // 'split' returns an array of string.
        String[] data = stringData.split(" ");
        for (String s : data){
            // Create new item with value set to the string s
            // Add item to our linked list.
            tree.addItem(new Node(s));
        }

       // tree.traverse(tree.getRoot());

        tree.removeItem(new Node("3"));
        tree.traverse(tree.getRoot());

        tree.removeItem(new Node("5"));
        tree.traverse(tree.getRoot());

        tree.removeItem(new Node("0"));
        tree.removeItem(new Node("4"));
        tree.removeItem(new Node("2"));
        tree.traverse(tree.getRoot());

        tree.removeItem(new Node("9"));
        tree.traverse(tree.getRoot());

        tree.removeItem(new Node("8"));
        tree.traverse(tree.getRoot());

        tree.removeItem(new Node("6"));
        tree.traverse(tree.getRoot());

        tree.removeItem(tree.getRoot());
        tree.traverse(tree.getRoot());

        tree.removeItem(tree.getRoot());
        tree.traverse(tree.getRoot());
    }
}


/*

MyLinkedList list = new MyLinkedList(null);
        list.traverse(list.getRoot());

        // Create a string of data array to avoid typing loads of addItems instructions.
        // String stringData = "Darwin Canberra Brisbane Perth Melbourne Canberra Adelaide Sydney";
        String stringData = "1 7 4 8 9 0 3 2 5 6";

        // Splits the various capital cities of Australia.
        // 'split' returns an array of string.
        String[] data = stringData.split(" ");
        for (String s : data){
            // Create new item with value set to the string s
            // Add item to our linked list.
            list.addItem(new Node(s));
        }

        list.traverse(list.getRoot());
        list.removeItem(new Node("3"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("5"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("0"));
        list.removeItem(new Node("4"));
        list.removeItem(new Node("2"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("9"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("8"));
        list.traverse(list.getRoot());

        list.removeItem(new Node("6"));
        list.traverse(list.getRoot());

        list.removeItem(list.getRoot());
        list.traverse(list.getRoot());

        list.removeItem(list.getRoot());
        list.traverse(list.getRoot());

 */
