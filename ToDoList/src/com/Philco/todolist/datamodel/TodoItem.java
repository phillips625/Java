package com.Philco.todolist.datamodel;

import java.time.LocalDate;

/**
 * Created by PhillipsDaramola on 21/12/2017.
 */

            // THIS IS THE 'M' PART OF THE MVC MODEL

// Each list view will have a short description, the actual description and the datepicker that is associated to the item.

public class TodoItem {

    private String shortDescription;
    private String details;
    private LocalDate deadline;

    public TodoItem(String shortDescription, String details, LocalDate deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.deadline = deadline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    // Because the items are displayed on the UI via the toString method, we have to override the toString method to display
    // what we want (as opposed to the object references that gets printed out by default by the toString method).

//    @Override
//    public String toString() {
//        return shortDescription;
//    }
}
