package com.philco;

// Step 1: Add the 'sqlite-jdbc...jar' file to the library -> File > Project Structure > Library > Add sign at the top, locate jar file, click ok,
// click ok.

import java.sql.*;

public class Main {

    // Ensure that we are not hardcoding anything.
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/PhillipsDaramola/Desktop/Algorithms/Java/Udemy Course/JavaPrograms/TestDB/" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static void main(String[] args) {

        try {
            // Establishing a connection to the database.
            // We're creating a database in this folder.
            Connection conn= DriverManager.getConnection(CONNECTION_STRING);
            // This ensures that nothing is inserted into the table.
//            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            // Drop table if it exists.
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            // The contacts table will be created in the testjava database.
            // Since we can only create the table once, we are using 'if not exists' so we don't try to create the table again.
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                    " (" + COLUMN_NAME + " text, " +
                                           COLUMN_PHONE + " integer, " +
                                           COLUMN_EMAIL + " text" +
                                    ")");

                    // Inserts an entry.
//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                    " (" + COLUMN_NAME + ", " +
//                        COLUMN_PHONE +  ", "  +
//                        COLUMN_EMAIL +
//                    ") " + "VALUES('Tim', 82832, 'tim@email.com')");
                            // OR
            insertContact(statement, "Tim", 82832, "tim@email.com");
            insertContact(statement, "Joe", 635332, "joe@email.com");
            insertContact(statement, "James", 23434, "james@email.com");
            insertContact(statement, "Rogan", 23434, "rogan@email.com");

            // Update record in table

            // Delete record

//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                                "VALUES('Tim', 12345, 'tman@email.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                                "VALUES('Joe', 12345, 'tman@email.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                                "VALUES('Jane', 3536, 'jane@email.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                                "VALUES('Jake', 6422, 'jake@email.com')");

//            statement.execute("UPDATE contacts SET phone = 82382 WHERE name = 'Jane'");
//            statement.execute("DELETE FROM contacts WHERE name = 'Joe'");


//            statement.execute("SELECT * FROM contacts");
//            // We need to use a different 'Statement' object in order to query the same result sets. A statement object can only ever have one result
//            // set associated with it.
//            ResultSet results = statement.getResultSet();
                                    // OR
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);


            // Looping through each record.
            while (results.next()){
                // The strings in the parenthesis must correspond to the column names.
                System.out.println(results.getString(COLUMN_NAME) + " " +
                                    results.getInt(COLUMN_PHONE) + " " +
                                    results.getString(COLUMN_EMAIL));
            }
            results.close();

            // ALWAYS CLOSE THE CONNECTION.
            // Always close the CONNECTION last.
            statement.close();
            conn.close();
        }
        catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

        // Insert contacts
    public static void insertContact(Statement statement, String name, int phone, String email) throws SQLException{

        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE +  ", "  +
                COLUMN_EMAIL +
                ") " + "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}


/*

        try {
            // Establishing a connection to the database.
            // We're creating a database in this folder.
            Connection conn= DriverManager.getConnection("jdbc:sqlite:/Users/PhillipsDaramola/Desktop/Algorithms/Java/Udemy Course/JavaPrograms/TestDB/testjava.db");
            Statement statement = conn.createStatement();
            // The contacts table will be created in the testjava database.
            statement.execute("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT)");
        }
        catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
 */