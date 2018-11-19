package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label label;

    public void initialize(){

    }

    // Zooms in on the label when the user hovers over the label
    @FXML
    public void handleMouseEnter(){
        // Zooming the size of the label.
        label.setScaleX(2.0);
        label.setScaleY(2.0);
    }

    // Zooms out on the label when the user's mouse leaves the label
    @FXML
    public void handleMouseExit(){
        // Zooming the size of the label.
        label.setScaleX(1.0);
        label.setScaleY(1.0);
    }
}
