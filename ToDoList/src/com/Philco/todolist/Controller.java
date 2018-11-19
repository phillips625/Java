package com.Philco.todolist;

                        // THIS IS THE 'C' PART OF THE MVC MODEL

import com.Philco.todolist.datamodel.TodoData;
import com.Philco.todolist.datamodel.TodoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;
    // Context menu is what appears when you right click on a menu.
    @FXML
    private ContextMenu listContextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;

    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodaysItems;

    // Initialise our application with some sample data.
    public void initialize(){

        listContextMenu = new ContextMenu();
        // This will be the menu that people see when they right click on an item.
        MenuItem deleteMenuItem = new MenuItem("Delete");
        // This is the event handler when the user right clicks on an item.
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Getting the item that is currently being selected in the list.
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        // Adding the deleteMenuItem to our CONTEXT MENU.
        // Now we have added the delete option to our context menu.
        listContextMenu.getItems().addAll(deleteMenuItem);

//        TodoItem item1 = new TodoItem("Mail birthday card 1", "Buy a 30th birthday card for John",
//                LocalDate.of(2016, Month.APRIL, 25));
//        TodoItem item2 = new TodoItem("Mail birthday card 2", "Second item",
//                LocalDate.of(2016, Month.MAY, 23));
//        TodoItem item3 = new TodoItem("Mail birthday card 3", "Third item",
//                LocalDate.of(2016, Month.APRIL, 22));
//        TodoItem item4 = new TodoItem("Mail birthday card 4", "Fourth item",
//                LocalDate.of(2016, Month.MARCH, 25));
//        TodoItem item5 = new TodoItem("Mail birthday card 5", "Fifth item",
//                LocalDate.of(2016, Month.APRIL, 20));
//
//        todoItems = new ArrayList<>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        // Temporary bit of code.
//        // This will ensure that we are now storing our data in a file (as opposed to hard coded like it is here).
//        TodoData.getInstance().setTodoItems(todoItems);

        // THIS IS A GENERIC EVENT HANDLER THAT HANDLES ANYTIME AN ITEM IS CLICKED - onMouseClicked="#handleClickListView"
        // CAN NOW BE REMOVED FROM THE FXML FILE.
        // Because the first item was selected in 'todoListView.getSelectionModel().selectFirst();' below, the anonymous
        // function picks on this and automatically shows the description of the first item (even before clicking anything).

        // ChangeListener is an anonymous class. We are using an anonymous class for the event handler.
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {

                if (newValue != null){
                    // This line kois an event listener code below - which can be replaced by lambda expressions.
                    // Get the selected item (which is in TodoItem format).
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    // Shows details when you click on the List View
                    itemDetailsTextArea.setText(item.getDetails());

                    // MMMM d, yyyy is the format of the date we are going to be using.
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                                // OR
                        // Another date format.
                    // yy means 17. yyyy means 2017.
                    // DateTimeFormatter df = DateTimeFormatter.ofPattern("d M yy");

                    // Shows the date when you click on the List View. Because getDeadline is in a LocalDate format, we are
                    // calling the toString method on it in order to display the text format.
                    // deadlineLabel.setText(item.getDeadline().toString());
                                    // OR
                    // Using the date formatter
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

            // Initialising the predicates.
        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return (todoItem.getDeadline().equals(LocalDate.now()));
            }
        };

        // "TodoData.getInstance().getTodoItems()" returns the list we want filtered.
        // The filteredList will call the test method for every item in the list that has been passed to it. If the test method returns
        // true, the item passes the filter. If the test method returns false, the item fails the filter and will not be kept. In this
        // example, we want to keep the items in the list that have a deadline of today.

//        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(),
//                new Predicate<TodoItem>() {
//                    @Override
//                    public boolean test(TodoItem item) {
//                        // This will give us a list of ALL the items by default.
//                        return true;
//                    }
//                });

                            // OR
        // Because we have creates a wantAllItems predicate, we can make the above code much cleaner.
        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);

        // The second item is the 'Comparator' anonymous class
//        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(TodoData.getInstance().getTodoItems(),

            // Passing the filtered list instead of the default sorted list - as above.
        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList,
                new Comparator<TodoItem>() {
            // The 2 items to be compared are o1 and o2.
                    // If o1 is smaller than o2, it MUST -1
                    // If o1 is equal to o2, it MUST 0
                    // If o1 is greater than o2, it MUST +1
                    @Override
                    public int compare(TodoItem o1, TodoItem o2) {
                        // Because the compareTo method in the date class does the exact comparison outlined above (i.e. if the first
                        // date is smaller than the second date, it returns -1, etc), we only need one line of code.
                        return o1.getDeadline().compareTo(o2.getDeadline());
                    }
                });

        // Returning an ObservableList in 'TodoData.java' and this line of code helps us to data bind the list view with the
        // ObservableList in the tododata class.
//        todoListView.setItems(TodoData.getInstance().getTodoItems());

                    // OR
        // Instead of using the observable array list above, we are using the sorted list here instead.
        // NOW, THE TO DO ITEMS WILL BE ORDERED BASED ON THE DEADLINES
        todoListView.setItems(sortedList);

        // Populate the ListViews in the UI (aka fxml file).
        // setAll sets our to do list items.
        // todoListView.getItems().setAll(todoItems);
        // Now the data will be coming from our singleton instead of the 'todoItems' arraylist,
        // todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());

        // Single selection mode: This will mean that we can only select one item at a time.
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // Automatically selects the first item.
        todoListView.getSelectionModel().selectFirst();

        // Controls each cell on the left (where the to do lists are).
        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {

            // Call back function.
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {

                ListCell<TodoItem> cell = new ListCell<TodoItem>(){

                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        // We can now comment the toString method in the TodoItem class.
                        super.updateItem(item, empty);

                        // The codes above have been left alone in their default state.
                        if (empty){
                            setText(null);
                        }
                        else {
                            setText(item.getShortDescription());
                            // The item will be in red if the deadline is today.
//                            if (item.getDeadline().equals(LocalDate.now())){

                            // Any date before today's date or 1 day prior will now be flagged in red.
                            if (item.getDeadline().isBefore(LocalDate.now().plusDays(1))){
                                setTextFill(Color.RED);
                            }
                            // The item will be YEif the deadline is the next day (from today).
                            else if (item.getDeadline().equals(LocalDate.now().plusDays(1))){
                                setTextFill(Color.BROWN);
                            }
                        }
                    }
                };

                // Creating the cell for the context menu.
                // The parameter in the addListener function uses a lambda expression (another way of representing a
                // standard function in the case, a listener method).
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) ->{
                            // If the cell is empty, set the context menu to null (aka turn it off).
                            if (isNowEmpty){
                                cell.setContextMenu(null);
                            }
                            // Else, create a cell for the context menu we have created. When a cell is nonempty, the context menu
                            // will get associated with it.
                            // Because of data binding, the data in the view was deleted AUTOMATICALLY.
                            else{
                                cell.setContextMenu(listContextMenu);
                            }

                });

                return cell;
            }
        });
    }

    // Shows new Dialog box that allows you to add a new to do list when you click a button.
    @FXML
    public void showNewItemDialog(){

        Dialog<ButtonType> dialog = new Dialog<>();

        // Sets the title of the dialog box
        dialog.setTitle("Add New Todo Item");
        // This is placed above the 'headerText' tag in the 'todoItemDialog.fxml' file.
        dialog.setHeaderText("Use this dialog to create a new todo item");
        // We want the dialog to be modal - meaning that while the dialog is visible, the user will not be able to interact other part
        // of the application's UI. So the user will have to cancel the window or click the cancel button in order to interact with the
        // UI.
        // We load our 'mainwindow.fxml' file in Main.java.
        dialog.initOwner(mainBorderPane.getScene().getWindow());

        //Gets the to do item from the dialog pane.
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            // Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
            // dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().setContent(fxmlLoader.load());

        }
        catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // Creating the OK button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        // Creating the Cancel button
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        //showandwait method waits for the input (ok or cancel) from the user.
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){

            DialogController controller = fxmlLoader.getController();

            TodoItem newItem = controller.processResults();

            // Updates the view with the new item MANUALLY. This has now been replaced by the ObservableList.
//            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            // Automatically selects new item (in the item list) when you add a new item
            todoListView.getSelectionModel().select(newItem);
//            System.out.println("OK pressed");
        }
//        else {
//            System.out.println("Cancel pressed");
//        }
    }

    // When the delete key is pressed, delete the to do list item.
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent){
        // Get the selected item.
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        // Checks if the selected item is not null and the delete key is pressed.
        if (selectedItem != null){
            if (keyEvent.getCode().equals(KeyCode.DELETE)){
                deleteItem(selectedItem);
            }
        }
    }

    @FXML
    public void handleClickListView(){
        // SelectionModel - This class tracks which item is selected.
        // We are using getSelectedItem rather then getSelectedItems because we are using a single selection mode (SelectionMode.SINGLE
        // in the initialize function).
        // This returns the TodoItems object at the position in the ListView.
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();

        itemDetailsTextArea.setText(item.getDetails());
        // The String version of the date.
        deadlineLabel.setText(item.getDeadline().toString());

//        // StringBuilder basically builds up Strings.
//        // Firstly, we're adding the string (description) we get from item.getDetails() to the StringBuilder.
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        // Get the string representation of our date.
//        sb.append(item.getDeadline().toString());
//
////        System.out.println("The selected item is " + item);
//
//        // Populating the extended text area with the details the selected item - specified above.
//        itemDetailsTextArea.setText(sb.toString());
    }

    // This will show the confirmation dialog box when you right click on an item and click the delete option.
    public void deleteItem(TodoItem item){
        // JavaFX has 5 canned dialog boxes - we're going to be using one of them (the confirmation alert box).
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // Setting the properties of the confirmation (are you sure you want to delete) alert box.
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete Item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press Ok to confirm or cancel to back out");

        // Now we are showing the dialog.
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user click the OK button or the cancel button.
        if (result.isPresent() && (result.get() == ButtonType.OK)){
            TodoData.getInstance().deleteTodoItem(item);
        }
    }

    @FXML
    public void handleFilterButton(){
        // WE'RE CREATING A NEW PREDICATE EACH TIME THE BUTTON IS PRESSED - WHICH IS INEFFICIENT. In order to make this process more
        // efficient, we'll declare 2 predicates (that receives the TodoItem type), then we initialise the predicates (in the initialise
        // method).

        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();

        // If the button is selected.
        if (filterToggleButton.isSelected()){
            filteredList.setPredicate(wantTodaysItems);
            // If there are no items with a deadline of today.
            if (filteredList.isEmpty()){
                itemDetailsTextArea.clear();
                deadlineLabel.setText("");
            }
            // Else if there are items with a deadline of today
            else if (filteredList.contains(selectedItem)){
                todoListView.getSelectionModel().select(selectedItem);
            }
            // Else show the first item only
            else{
                todoListView.getSelectionModel().selectFirst();
            }
        }

        // Else, if the button is not selected, show everything.
        else {
            filteredList.setPredicate(wantAllItems);
            todoListView.getSelectionModel().select(selectedItem);
        }
    }

    // Exit the application.
    @FXML
    public void handleExit(){
        Platform.exit();
    }

                    // FORMER handleFilterButton FUNCTION.

//    public void handleFilterButton(){
//        // WE'RE CREATING A NEW PREDICATE EACH TIME THE BUTTON IS PRESSED - WHICH IS INEFFICIENT. In order to make this process more
//        // efficient, we'll declare 2 predicates (that receives the TodoItem type), then we initialise the predicates (in the initialise
//        // method).
//
//        // If the button is selected.
//        if (filterToggleButton.isSelected()){
//            filteredList.setPredicate(new Predicate<TodoItem>() {
//                @Override
//                public boolean test(TodoItem todoItem) {
//                    // Adds the item to list only if the deadline is today's date.
//                    return (todoItem.getDeadline().equals(LocalDate.now()));
//                }
//            });
//        }
//
//        // Else, if the button is not selected, show everything.
//        else {
//            filteredList.setPredicate(new Predicate<TodoItem>() {
//                @Override
//                public boolean test(TodoItem item) {
//                    // Return true - this will return all the items.
//                    return true;
//                }
//            });
//        }
//    }
}
