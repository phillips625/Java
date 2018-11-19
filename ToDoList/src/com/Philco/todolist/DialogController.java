package com.Philco.todolist;

import com.Philco.todolist.datamodel.TodoData;
import com.Philco.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Created by PhillipsDaramola on 24/12/2017.
 */
public class DialogController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;



    // This will process the result after OK is pressed.
    // In here, we want to gather the user's input, create a new to do item and add it to our list in our todoData instance.
    public TodoItem processResults(){

        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadlineValue = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
        // Adding the item to the singleton instance.
        TodoData.getInstance().addTodoItem(newItem);
        // Returning item that was newly created
        return newItem;
    }
}
