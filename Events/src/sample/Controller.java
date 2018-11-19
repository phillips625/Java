package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.event.ActionEvent;


// Event handler.

public class Controller {
    // The @FXML associates the nameField in the fxml file to the one initialised here. The name of these fields should match
    // the id names in the sample.fxml file.
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

//    This initialisation code will be run when the application first runs.
    @FXML
    public void initialize(){
        // We want disable both buttons initially - as the text box will be empty at this stage.
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

//    Now we have to associate the method to the Button class in the fxml file.
//    You don't need to add @FXML to this event handler, but it is good to add it for readability.
//    We are adding 'ActionEvent' in case we want to use the same event handler for more than one control (button control
//  and text control).
    @FXML
    public void onButtonClicked(ActionEvent e){
        // If the event handler has been invoked for the hello button.
        // This is an example of when you have one event handler for 2 buttons.
        if(e.getSource().equals(helloButton)){
            System.out.println("Hello, " + nameField.getText());
        }

        else if(e.getSource().equals(byeButton)){
            System.out.println("Bye, " + nameField.getText());
        }

                    // THIS IS BEING RUN ON THE BACKGROUND THREAD

        // This will be run in the background (put the background thread to sleep) as opposed to being run by the
        // UI thread. This is basically another
        // instance of a program that will be running in the background.
        Runnable task = new Runnable() {
            @Override
            public void run() {

                // The UI becomes unresponsive when the UI Thread is executing an event handler. This is an experiment to show that in
                // action - putting the UI to sleep for 10 seconds.
                try{
                    String s = Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    Thread.sleep(10000);

                                // THIS IS BEING RUN ON THE UI THREAD
                    // Platform.runLater IS ONE WAY TO MAKE SURE THE CODE RUNS ON THE UI THREAD.

                    // runLater will ensure that the UI is updated without any exceptions (while we wait for 10 seconds
                    // for the above background thread to run - during this wait, we can still interact with the controls
                    // on the UI). This time, it is actually the Java Fx thread doing the update
                    // as opposed to another thread.
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            String s = Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the: " + s);

                            // We are going to update our label after the thread has slept.
                            // This will throw an error as we are trying to update a node (label field) using a background thread.
                            // ONLY THE JAVA FX thread is allowed to make any update to a node in the scene graph.
                            ourLabel.setText("We did something!");
                        }
                    });
                }
                catch(InterruptedException event){

                }
            }
        };

        // This will run the above thread.
        new Thread(task).start();

        // If the checkbox is selected, clear the text box and then print 'hello textname' or 'bye textname'
        if(ourCheckBox.isSelected()){
            nameField.clear();
            // Disable the buttons after the text field is cleared.
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }


//        System.out.println("Hello, " + nameField.getText());
////        getSource tells us what control was pressed. It uses the standard toString method.
//        System.out.println("The following button was pressed: " + e.getSource());
    }

//    We want the button disabled when the text field is empty and activated only when they have fed in an input into the text
    // box.
    // This is called when you take your fingers away from the keyboard
    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        // With 'trim()', we're basically ignoring all white spaces aka the space key.
        // If either of these conditions is true, then disableButtons will be set to true.
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    // Print the moment the check box is selected in the console.
    public void handleChange(){
        System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }
}
