package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by PhillipsDaramola on 09/01/2018.
 */

// We're creating a class for each of the tables so that the users will not know about the internal workings of the DataSource class.
public class Artist {

    // We are changing from int to SimpleIntegerProperty and String to SimpleStringProperty - so we can take advantage of
    // DATA BINDING.

//    private int id;
    private SimpleIntegerProperty id;

//    private String name;
    private SimpleStringProperty name;

    // When an Artist constructor is created, these simple properties will get initialised.
    public Artist() {
        // Initializing the simple properties.
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        // This is how you set the id into a SimpleIntegerProperty.
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
