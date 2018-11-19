package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.DataSource;

// We use a Singleton if we want an application to only be able to CREATE ONE INSTANCE OF A CLASS. EVERY OBJECT THAT WANTS TO
// USE THE METHOD IN THAT CLASS WILL HAVE TO CALL THE SAME SINGLETON.

public class Main extends Application {

    // The controller is not created until the main.fxml is loaded (in the start method).
    @Override
    public void start(Stage primaryStage) throws Exception{

        // The purpose of this separated part is to list out all the artists from the start - aka pre-populate the table view. .
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        // Getting an instance of the Controller class.
        Controller controller = loader.getController();
        // Querying the artists.
        controller.listArtists();

        primaryStage.setTitle("Music Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

        // Open the database
        // Message if we can't connect to the database.
        if (!DataSource.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't connect to database");
            // Exits the application immediately if we are not able to connect to the database.
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        // Close the database
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
