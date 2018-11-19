package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import sample.model.Album;
import sample.model.Artist;
import sample.model.DataSource;

public class Controller {

    @FXML
    private TableView artistTable;

    // When fetching data from the database, we need to make the progess bar visible. When the task is done, we need to make
    // it invisible. We can can't set the visiblity from inside the call method (in the GetAllArtistsTask class) because any
    // code that touches a UIControl has to run on the UI thread.
    @FXML
    private ProgressBar progressBar;

    // Function could be used as an event handler for a button and also a normal method in this class.
    @FXML
    public void listArtists(){
        // You always want to run a query on the background thread using a TASK.
        Task<ObservableList<Artist>> task = new GetAllArtistsTask();
        // Data BINDING the ui with the database.
        // Binding the result of the task to the table's ObservableList property.
        artistTable.itemsProperty().bind(task.valueProperty());

        // Binding the progress of loading data from the database to the slider.
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        // You always want to run a query on the background thread using a TASK. We then have to update the UI using the
        // Java FX Application UI thread.
        // IF YOU'RE USING DATABINDING, THE UI CODE WILL RUN ON THE UI THREAD AUTOMATICALLY.
        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();

        // THIS IS VERY IMPORTANT - AS THIS IS REQUIRED TO START THE 'TASK'.
        new Thread(task).start();
    }

    // For the 'Query Albums By Artist' button.
    @FXML
    public void listAlbumsForArtist(){

        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if (artist == null){
            System.out.println("No Artist Selected");
            return;
        }

        // Anonymous Task.
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                // This gives us the list of albums for the artist.
                return FXCollections.observableArrayList(
                        DataSource.getInstance().queryAlbumsForArtistId(artist.getId()));
            }
        };

        artistTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

    @FXML
    public void updateArtist() {
//        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();

        // Getting the record we want to update
        // Because 'AC/DC' is in position 3 (on the table list view when you run the application), we are setting the
        // 'get' to position 2.
        final Artist artist = (Artist) artistTable.getItems().get(2);

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().updateArtistName(artist.getId(), "AC/DC");
            }
        };

        task.setOnSucceeded(e -> {
            // Checking that the update succeeded. If true, the we update the UI.
            if(task.valueProperty().get()) {
                // If true, the we update the UI.
                artist.setName("AC/DC");
                // Then we refresh the table. We should not have to refresh the table - so this is a work-around.
                artistTable.refresh();
            }
        });

        // Invoking the above thread.
        new Thread(task).start();
    }

}

    // SEPARATING THE TASK CLASS FROM THE CONTROLLER CLASS ABOVE AS THE TASK CLASS RUNS ON THE BACKGROUND THREAD WHILST THE
// CONTROLLER CLASS RUNS ON THE UI THREAD.

// The reason we are creating a separate class is because we may need to use this task in 2 places - at the start and
// when the user asks to see all artists.
class GetAllArtistsTask extends Task{

    @Override
    public ObservableList<Artist> call() throws Exception{
        return FXCollections.observableArrayList
                (DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC));
    }
}