package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //JavaFx actually runs css in the background - its default theme is the MODENA theme. We can change this default theme to
        //what we want (e.g to the Kaspian theme).
        // Order of Precedences - Inline css takes predence over the fxml stylesheet which takes precedence over the
        // application's theme stylesheet. When changing the fxml style, we are actually overriding the THEME that has been set
        // by the application.
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
