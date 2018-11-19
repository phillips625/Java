package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

                    // Code Vs Fxml

// In the Application class, 3 method are the most important:
// 1 start method
// 2 init method
// 3 launch method
public class Main extends Application {

    // This method has got all the mechanisms to kick start our application.
    // 'Stage primaryStage' parameter is a top level java fx container that extends the window class. Think about it as a main
    // window. Java fx run time constructs the window and passes it as a parameter

            // Theatre analogy
    // The window (that opens up) is a stage.
    // Scene is basically the stuff in the scene.
            // Scene is basically like Scene makes it easy to reuse the stage container.
    @Override
    public void start(Stage primaryStage) throws Exception{

        // This loads the UI from the 'fxml' file.
                        // For Code Vs Fxml
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


//                            // For Code Vs Fxml
//                                //Using code here
//        GridPane root = new GridPane();
//
//        // This next 3 lines replaces " alignment="center" hgap="10" vgap="10" " in the Fxml file.
//        root.setAlignment(Pos.CENTER);
//        // Sets vertical distances between grids
//        root.setVgap(10);
//        root.setHgap(10);

        // Window class is the parent of the Stage class - Stage is the top level of the Java fx container. So window
        // title will be set to Hello World.
        primaryStage.setTitle("Hello World");

        // Every stage requires a scene
        primaryStage.setScene(new Scene(root, 300, 275));

//        Label greeting = new Label("Welcome to JavaFX");
//        // Changes the color of the text in green
//        greeting.setTextFill(Color.GREEN);
//        // Set font weight and size
//        // font is static - meaning there is only one copy in memory.
//        greeting.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));
//        // This is how you control to the grid - by using "root.getChildren().add(...".
//        root.getChildren().add(greeting);

        // show method shows the ui aka launches the application.
        primaryStage.show();
    }


    public static void main(String[] args) {

        // This automatically launches the application until the close button is clicked.
        launch(args);
    }
}
