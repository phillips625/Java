
package com.Philco;

public class SearchTree implements NodeList {

    private ListItem root = null;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem newItem) {
        if (this.root == null) {
            // the tree was empty, so our item becomes the head of the tree
            this.root = newItem;
            return true;
        }

        // otherwise, start comparing from the head of the tree
        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if (comparison < 0) {
                // newItem is greater, move right if possible
                if (currentItem.next() != null) {
                    currentItem = currentItem.next();
                } else {
                    // there's no node to the right, so add at this point
                    currentItem.setNext(newItem);
                    return true;
                }
            } else if (comparison > 0) {
                // newItem is less, move left if possible
                if (currentItem.previous() != null) {
                    currentItem = currentItem.previous();
                } else {
                    // there's no node to the left, so add at this point
                    currentItem.setPrevious(newItem);
                    return true;
                }
            } else {
                // equal, so don't add
                System.out.println(newItem.getValue() + " is already present");
                return false;
            }
        }
        // we can't actually get here, but Java complains if there's no return
        return false;
    }

    // If the item has no children, then the link to it can be set to null.
    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }
        ListItem currentItem = this.root;
        ListItem parentItem = currentItem;

        while (currentItem != null) {
            int comparison = (currentItem.compareTo(item));
            if (comparison < 0) {
                parentItem = currentItem;
                currentItem = currentItem.next();
            } else if (comparison > 0) {
                parentItem = currentItem;
                currentItem = currentItem.previous();
            } else {
                // equal: we've found the item so remove it
                performRemoval(currentItem, parentItem);
                return true;
            }
        }
        return false;
    }


    private void performRemoval(ListItem item, ListItem parent) {
        // remove item from the tree
        if (item.next() == null) {
            // no right tree, so make parent point to left tree (which may be null)
            if (parent.next() == item) {
                // item is right child of its parent
                parent.setNext(item.previous());
            } else if (parent.previous() == item) {
                // item is left child of its parent
                parent.setPrevious(item.previous());
            } else {
                // parent must be item, which means we were looking at the root of the tree
                this.root = item.previous();
            }
        } else if (item.previous() == null) {
            // no left tree, so make parent point to right tree (which may be null)
            if (parent.next() == item) {
                // item is right child of its parent
                parent.setNext(item.next());
            } else if (parent.previous() == item) {
                // item is left child of its parent
                parent.setPrevious(item.next());
            } else {
                // again, we are deleting the root
                this.root = item.next();
            }
        } else {
            // neither left nor right are null, deletion is now a lot trickier!
            // From the right sub-tree, find the smallest value (i.e., the leftmost).
            ListItem current = item.next();
            ListItem leftmostParent = item;
            while (current.previous() != null) {
                leftmostParent = current;
                current = current.previous();
            }
            // Now put the smallest value into our node to be deleted
            item.setValue(current.getValue());
            // and delete the smallest
            if (leftmostParent == item) {
                // there was no leftmost node, so 'current' points to the smallest
                // node (the one that must now be deleted).
                item.setNext(current.next());
            } else {
                // set the smallest node's parent to point to
                // the smallest node's right child (which may be null).
                leftmostParent.setPrevious(current.next());
            }
        }
    }

    @Override
    public void traverse(ListItem root) {
        // recursive method
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }

    }
}

/*
							----------- MY CODE ------------------
    package com.Philco;

    // SearchTree is similar to LinkedList
        // Just like 'MyLinkedList', 'SearchTree' implements 'NodeList'.
    public class SearchTree implements NodeList{

        private ListItem root = null;

        public SearchTree(ListItem root) {
            this.root = root;
        }

        @Override
        public ListItem getRoot() {
            return null;
        }

        // In this case, we are going from left to right, adding a new node where the node is null.
        // Follows the same structure as the LinkedList, but Instead of breaking and recreating the links to insert - we're going to be
        // traversing the tree itself - aka moving left to right until the node is null.
        @Override
        public boolean addItem(ListItem newItem) {

            if (this.root == null){
                // The tree was empty, so our item becomes the head of the tree.
                // This is the first item on the list. The list was empty, so this item becomes the head of the list.
                this.root = newItem;
                return true;
            }
            // Otherwise, start comparing from the head of the tree.
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
                        // there's no node to the right, so add at this point.
                        /*
                        currentItem.setNext(newItem);
                        // The new item now points back to the previous item (aka the ex current item).
                        newItem.setPrevious(currentItem);
                        return true;

    // OR
    // setNext returns the right item. We know that the next item is null, hence we can now add our NEWITEM.
                        currentItem.setNext(newItem);
                                return true;
                                }
                                }

                                else if (comparison > 0){
                                // newItem is less, move left if possible.
                                if (currentItem.previous() != null) {

                                currentItem = currentItem.previous();
                                }

                                else {
                                // There is no node to the left, so add to this point. In this case, the previous item is null, hence we insert the
                                // new item.
                                currentItem.setPrevious(newItem);
                                return true;
                                }

                                }
                                // If the current and new item is the same. Equal, so don't add. We are not going to be adding duplicates.
                                else {

                                System.out.println(newItem.getValue() + " is already present, not added.");
                                return false;
                                }
                                }
                                // We can't actually get here, but Java complains if there's no return.
                                return false;
                                }

    @Override
    public boolean removeItem(ListItem item) {
            return false;
            }

    @Override
    public void traverse(ListItem root) {

            // RECURSIVE METHOD - Refer to the SCREENSHOT.
            if (root != null) {
            // root = Darwin. root.previous() means Darwin will be going to the left (to Brisbane). Traverse is called again -
            // it goes to Brisbane (which is not null), then traverse is called again with Adelaide. When 'root' is Adelaide, it has no left node,
            // hence null is returned. Now 'System.out.println(root.getValue());' is called and 'Adelaide' is printed at this point.
            // 'traverse(root.next());' is then called - Adelaide.next() which is null - then it goes to the previous node - which is Brisbane and
            // on and on.
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
            }
            }
            }


            ------------------ Seeing what is going on. -------------------------
    public boolean addItem(ListItem newItem) {
            System.out.println("Working on  " + newItem.getValue());
            if (this.root == null) {
            // the tree was empty, so our item becomes the head of the tree
            this.root = newItem;
            return true;
            }

            // otherwise, start comparing from the head of the tree
            ListItem currentItem = this.root;
            while (currentItem != null) {
            int comparison = (currentItem.compareTo(newItem));
            if (comparison < 0) {
            System.out.println(currentItem.getValue() + " < " + newItem.getValue());
            // newItem is greater, move right if possible
            if (currentItem.next() != null) {
            currentItem = currentItem.next();
            System.out.println("Moving right to " + currentItem.getValue());
            } else {
            // there's no node to the right, so add at this point
            currentItem.setNext(newItem);
            System.out.println("currentItem setNext to " + newItem.getValue());
            return true;
            }
            } else if (comparison > 0) {
            System.out.println(currentItem.getValue() + " > " + newItem.getValue());
            // newItem is less, move left if possible
            if (currentItem.previous() != null) {
            currentItem = currentItem.previous();
            System.out.println("currentItem setPrevious to " + currentItem.getValue());
            } else {
            // there's no node to the left, so add at this point
            currentItem.setPrevious(newItem);
            System.out.println("currentItem setPrevious now " + newItem.getValue());

            return true;
            }
            } else {
            // equal, so don't add
            System.out.println(newItem.getValue() + " is already present");
            return false;
            }
            }
            // we can't actually get here, but Java complains if there's no return
            return false;
            }

        */