// Because we changed the package name from 'sample' to 'com.Philco.todolist', we also have to let intelliJ know about this
// change by:
// 1 Clicking on the 'Main' button above
// 2 Click Edit Configuration
// 3 Change the 'Main class' field from 'sample' to 'com.Philco.todolist.Main'.

package com.Philco.todolist;

import com.Philco.todolist.datamodel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("To do List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    // This method runs alongside the exit button - which is used to close the window. Anytime we close our application,
    // this method will store our items - think about your mac Notes application.
    // We're overriding the stop method inherited from the Application class.
    // This method is intended to run once. We are going to be using this method to write all our hard coded 'to do list'
    // in the Controller class to a file and then closing that file afterwards.
    @Override
    public void stop() throws Exception {
        try {
            // This is how you access the Singleton.
            // 'TodoData.getInstance()' gets our singleton's instance. storeTodoItems() is a static method, so we can call it
            // this way. This line of code will save the data to the text file.
            TodoData.getInstance().storeTodoItems();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            // Loads the to do items from the txt file.
            TodoData.getInstance().loadTodoItems();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
