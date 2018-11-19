package com.Philco.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Created by PhillipsDaramola on 24/12/2017.
 */

// TodoData is a singleton class.
    // FOR ANY APPLICATION THAT WANTS TO ACCESS THE DATA (STORED IN THE todoItems LIST), IT HAS TO CALL
// 'TodoDate.getInstance.getToDoItems()' BECAUSE WE HAVE SET OUR CONSTRUCTOR TO BE A PRIVATE ONE - MEANING THAT YOU
// CAN'T INSTANTIATE AN OBJECT FOR THIS CLASS ANY OTHER WAY.

public class TodoData{
    // This is the instance of the singleton.
    // The Singleton class usually contains a static method that allows any class to get its single instance and call its methods.
// This means out controller class and our main class will have an easy way to access it methods.
// Of course the Singleton class has more functionality than that.
    private static TodoData instance = new TodoData();
    // Flat file where the client's input will be stored.
    private static String filename = "TodoListItems.txt";
    // The ObservableList enables the data binding to work - data binding (which is done by the creators of Java) allows the data
    // from the front end to be automatically synced o the backend.
    // Storing our to do items
    private ObservableList<TodoItem> todoItems;
    // Will be used to manipulate the date.
    private DateTimeFormatter formatter;

    // Returns the instances of our TodoData class. It is traditional to use a getInstance class when returning a singleton class
    // instance.
    public static TodoData getInstance(){
        return instance;
    }

    // In conjunction with the above method, this constructor below will ensure that we can't create another instance of this class.
    private TodoData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    // Method to return our to do list items.
    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void addTodoItem(TodoItem item){
        todoItems.add(item);
    }

    // We only needed this so that the Controller can store the hard coded items.
//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

    // Reads the to do items from a file.
    // Loads the to do items from a file to the 'todoItems' list we created.
    public void loadTodoItems() throws IOException{

        // The reason for using an 'observableArrayList' comes down to our controller (where we set up our todoListView).
        // The setAll method (used in the initialized method of the controller) requires that we have an array in a format that uses the
        // observableArrayList (according to Java).
        // 'observableArrayList' is part of the Java API. observableArrayList is used for performance purposed. observableArrayList
        // will raise events. FXCollections is optimised to reduce the number of events raised when the list is changed (aka added or
        // removed from). Always use FXCollections rather than the one from 'Java.util'.
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        // Points to the location where the file is.
        BufferedReader br = Files.newBufferedReader(path);

        // This string will contain the data for each line.
        String input;

        try {
            // We are going to be creating a loop that retrieves the data in the try block.
            while ((input = br.readLine()) != null){
                String [] itemPieces = input.split("\t");
                // Now that we have got the 3 items of information (from the oabove line of code), we want to convert them.
                // Description on the left hand side.
                String shortDescription = itemPieces[0];
                // Detail on the right hand side.
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                // Convert the date to a format the TodoItem constructor can read it in.
                // The formatter was set in the private constructor.
                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        }
        // The finally checks that we have a valid file object before we try to close it.
        finally {
            if (br != null){
                br.close();
            }
        }
    }

    // This will save the to do items to a file.
    public void storeTodoItems() throws IOException{

        // Path pointing to where we are going to save the to do items.
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            // Now we going to be iterating through a list of to do items and then save them one entry at a time to the text
            // file.
            // We can tried our record one entry (one entry of the TodoItem object) at a time using the iterator.
            Iterator<TodoItem> iter = todoItems.iterator();
            // Checking to see if there is another entry in the iterator that we can use.
            while (iter.hasNext()){
                // Grabbing the next entry from the iterator.
                TodoItem item = iter.next();
                // Now we want to write the content of the iterator to a file.
                // %s denotes string. \t denotes tab.
                // 'format' in 'item.getDeadline().format..' is be used to format the date to a format that is readable.
                // IN A REAL WORLD APPLICATION, RATHER THAN USING A TAB DELIMITER, WE WOULD STORE THE DATA AN XML FORMAT OR A DATABASE (
                // AS USING A TEXT FILE WHEN THERE ARE LOTS OF TO DO RECORDS CAN BECOME VERY IMPRACTICAL).
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));

                // Adds a new line to the text file.
                bw.newLine();
            }
        }
        finally {
            if (bw != null){
                bw.close();
            }
        }
    }

    public void deleteTodoItem(TodoItem item){
        todoItems.remove(item);
    }

}
