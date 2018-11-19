package com.Philco;

/**
 * Created by PhillipsDaramola on 05/10/2017.
 */
public class MyLinkedList implements NodeList{

    private ListItem root = null;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    // Adds item to the list. Works out if the item is the first on the list or if there are other items in the list, it works
    // out where the last item is and appends the item to the end of the list.
    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null){

            // This is the first item on the list. The list was empty, so this item becomes the head of the list.
            this.root = newItem;
            return true;
        }

        // Now we're comparing from the head of the list.
        ListItem currentItem = this.root;

        // If we reach a null, it means there is no more entry to go through in the list.
        while (currentItem != null){
            // Comparison will be return 0,1 or -1.
            int comparison = (currentItem.compareTo(newItem));

            // 'comparison < 0' - newItem is greater, move right if possible.
            if (comparison < 0){
                // If we can go forward/ go to the next item.
                if (currentItem.next() != null){
                    currentItem = currentItem.next();
                }
                // 'currentItem.next()' sends a null back - aka no item to go to next.
                else {
                    /*
                    currentItem.setNext(newItem);
                    // The new item now points back to the previous item (aka the ex current item).
                    newItem.setPrevious(currentItem);
                    return true;
                    */
                                // OR
                    // setNext returns the right item.
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            }

            else if (comparison > 0){
                // newItem is less, insert before.
                if (currentItem.previous() != null) {
                    // We're inserting the new item to what was the current item / the position of the current item.
                    // 'currentItem.previous()' returns a 'ListItem'.
                    // Set the new item to the right
                    currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                    newItem.setPrevious(currentItem.previous());
                    newItem.setNext(currentItem).setPrevious(newItem);
                }

                // If there is no previous item.
                else {
                    newItem.setNext(this.root).setPrevious(newItem);
                    // The new item is now the new root.
                    this.root = newItem;
                }
                return true;
            }
            // If the current and new item is the same.
            else {

                System.out.println(newItem.getValue() + " is already present, not added.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {

        if (item != null){
            System.out.println("Deleting item " + item.getValue());
        }

        ListItem currentItem = this.root;

        while (currentItem != null){
            int comparison = currentItem.compareTo(item);
            if (comparison == 0){
                // Found the item to delete
                if (currentItem == this.root){
                    this.root = currentItem.next();
                }

                // We're ensuring that the 'previous' and 'next' are point to the right places.
                else {
                    // We know there is a previous entry because we've already tested that there is a root (from the while loop).
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null){
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                // We did find the record, hence return true.
                return true;

            }

            // When we haven't reached the point in the linked list yet.
            else if (comparison < 0) {
                currentItem = currentItem.next();
            }

            // We've gone past the point we we could have actually deleted the item.
            else{
                // comparison > 0
                // We are at an item greater than the one to be deleted.
                // Hence, the item is not in the list.
                return false;
            }
        }
        // We've reached the end of the list
        // Without finding the item to delete.
        return false;
    }

    @Override
    public void traverse(ListItem root) {

        if (root == null){
            System.out.println("The list is empty");
        }
        else {
            while (root != null){
                System.out.println(root.getValue());
                root = root.next();
            }
        }

                            // OR
        // USE RECURSION - Doesn't make sense to use Recursion in this case as the nodes could be too long and would
        // eventually crash the computer. Only use recursion when the number of repetitions is not too much.
        // It would take a depth of 63 to go through 9, 000, 000, 000, 000, 000, 000 RECORDS / NODES!!!

//        if (root != null) {
//            traverse(root.next());
//        }
    }
}
