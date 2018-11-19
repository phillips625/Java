package com.philco.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhillipsDaramola on 05/01/2018.
 */

                            // TRANSACTIONS
    // A SEQUENCE OF SQL STATEMENTS THAT ARE RUN AS ONE ENTITY. IF ONE OD THE STATEMENTS FAILS, THE WHOLE TRANSACTION FAILS.
    // WE ONLY EVER NEED TRANSACTIONS WHEN WE ARE QUERYING THE DATABASE. TRANSACTIONS MUST FOLLOW THE ACID RULE.


public class DataSource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/PhillipsDaramola/Desktop/Algorithms/Java/Udemy Course/JavaPrograms/Music/" + DB_NAME;

        // Album table column names
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    // We're using the index number of the columns because it will make our code look cleaner. NB: if the column positions (e.g name
    // column moved from column 1 to column 2) change, you have change the numbers assigned here.
    // Much better and faster to loop through column positions than to loop through column names (as SQLite does not have to map each
    // column names to the specified 'String').
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

        // Artist table column names
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    // These are the column indexes. We'll be using these instead of the column names.
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

        // Songs table column names
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    // These are the column indexes. We'll be using these instead of the column names.
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // " = \"" is used to open a " around the returned artist name.
    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " +TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";



    public static final String QUERY_ARTIST_FOR_SONG_START =
            "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS +
                    " INNER JOIN " + TABLE_ALBUMS + " ON " +
                    TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " INNER JOIN " + TABLE_ARTISTS + " ON " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
                    " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";
    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
                    TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";



    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONG_ALBUM + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS +
            "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST +
            " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " ORDER BY " +
            TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK;


    public static final String QUERY_VIEW_SONG_INFO =  "SELECT " + COLUMN_ARTIST_NAME + ", " +
            COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONG_TITLE + " = \"";

                        // PREPARED STATEMENT.
    // USE PREPARED STATEMENTS TO PREVENT SQL INJECTION ATTACKS - BY ADDING A QUESTION MARK TO THE END OF YOUR QUERY.
    // The question mark place holder will be replaced by the actual title that the user wants to find.
    // The question mark ensures that the title typed in by the user will be taken LITERALLY as opposed to been seen as a SQL statement.
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " +
                                COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
                                " WHERE " + COLUMN_SONG_TITLE + " = ?";

                    // INSERT STATEMENTS.
    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

                    // QUERIES
    // The QUERY_SONG statement is needed - like the 2 queries below, it will be used to check if a song exists. If it does, then it
    // will not add the song otherwise, it will add it.
    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    //establish connection to database.
    private Connection conn;

                        // PREPARED STATEMENT INSTANCE VARIABLE.
    // THIS WILL CREATE THE PREPARED STATEMENT ONCE - SO WE DON'T HAVE TO CREATE IT EVERY TIME AN INSTANCE OF THIS CLASS IS CREATED.
    // THIS WILL HELP IMPROVE PERFORMANCE.
    private PreparedStatement querySongInfoView;

                        // Prepared Statements for INSERTS
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    // Open database
    // You can also use try with resources.
    public boolean open(){
        try {
            // Returns true if we can connect to database.
            conn = DriverManager.getConnection(CONNECTION_STRING);
                            // PREPARED STATEMENT INSTANCE VARIABLE.
            // THIS WILL CREATE THE PREPARED STATEMENT ONCE - SO WE DON'T HAVE TO CREATE IT EVERY TIME AN INSTANCE OF THIS CLASS IS CREATED.
            // THIS WILL HELP IMPROVE PERFORMANCE.
            // NB: All these connections have to be closed in the 'close' statement.
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            // We're inserting the key from the prepared statement object - 2nd parameter.
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            // We don't need the ids returned in this case. We are adding the 'Statement.RETURN_GENERATED_KEYS' in the above because we need to
            // pass them on to the insert statement.
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);

            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);

            return true;
        }
        catch (SQLException e){
            System.out.println("Couldn't connect to the database: " + e.getMessage());;
            return false;
        }
    }

    // Close database
    public void close(){
        try {

            // Closing the PreparedStatement.
            // Here, the order of the if statement is important as we need the connection to be open in order to close statement.
            if (querySongInfoView != null){
                querySongInfoView.close();
            }

            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }

            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }

            if(insertIntoSongs !=  null) {
                insertIntoSongs.close();
            }

            if(queryArtist != null) {
                queryArtist.close();
            }

            if(queryAlbum != null) {
                queryAlbum.close();
            }

            if (conn != null){
                conn.close();
            }
        }
        catch (SQLException e){
            System.out.println("Could not close connection: " + e.getMessage());
        }
    }

                    // ARTIST TABLE
    // Query the Artist table.
    public List<Artist> queryArtists(int sortOrder){

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        // This will accept any number - bumbers that are not specified above will automatically put the table in ascending order.
        if (sortOrder != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            // Case insensitive order.
            sb.append(" COLLATE NOCASE ");

            // The default order will be in ascending order.
            if (sortOrder == ORDER_BY_DESC){
                sb.append("DESC");
            }
            else {
                sb.append("ASC");
            }
        }
        // Using try with resources instead. Try with resources automatically closes the 'statement' and 'results' declarations - whether the
        // result returned is null or not (hence any object calling this method must have a way to handle a 'null' value that may have been
        // returned).
//        Statement statement = null;
//        ResultSet results = null;

        try (Statement statement = conn.createStatement();
//             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)){
                        // OR
             ResultSet results = statement.executeQuery(sb.toString())){

            // Using try with resources instead.
//            conn.createStatement();
//            // Returns all the artist's records.
//            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (results.next()){
                // Creating a new Artist object for each record.
                Artist artist = new Artist();
//                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                        // OR
                artist.setId(results.getInt(INDEX_ARTIST_ID));
//                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                        // OR
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;
        }

        catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

                        // ALBUM TABLE
    // Returning a list of the Album names.
    public List<String> queryAlbumsForArtist(String artistName, int sortOrder){

        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        // "\"" is used to close the " around the returned artist name.
        sb.append("\"");

        if(sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if(sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while(results.next()) {
                albums.add(results.getString(1));
            }

            return albums;

        } catch(SQLException e) {
            System.out.println("Query failed: "+ e.getMessage());
            return null;
        }

    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if(sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
            if(sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        // Just to check if there is an error with our query
        System.out.println("SQL Statement: " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();

            while(results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }

            return songArtists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    // Used to get information about a table - e.g column names. All you have to do is to remember the name of the table.
    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        // The table returned to the ResultSet is a different table from the table in the database.
        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            // GEtting the schema information from the table using the getMetaData method.
            ResultSetMetaData meta = results.getMetaData();
            // Gets the number of columns.
            int numColumns = meta.getColumnCount();
            for(int i=1; i<= numColumns; i++) {
                // Print each column name.
                System.out.format("Column %d in the songs table is names %s\n",
                        i, meta.getColumnName(i));
            }
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public int getCount(String table){
//        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;

        String sql = "SELECT COUNT(*) AS count FROM " + table;
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql)) {

            // The returns the result of the counting all the rows in column one.
            // 1 is the result you get from 'COUNT(*)' (the first column)- which is 5350.
//            int count = results.getInt(1);
                        // OR
            // The advantage of using this method is that if the position of the columns change (COUNT(*) and MIN(_id)), we don't have to
            // change the number that we pass in as an argument.
            int count = results.getInt("count");
            // 2 is the result you get from 'MIN(_id)' (the second column)- which is minumum value of '_id'.
//            int min = results.getInt(2);
                        // OR
//            int min = results.getInt("min_id");
//            System.out.format("Count = %d, Min = %d\n", count, min);
            System.out.format("Count = %d\n", count);

            return count;
        }

        catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    // When view is successfully created, return true, else return false.
    public boolean createViewForSongArtists() {

        // Because we don't want any results back, we don't have to include the ResultSet in the try with resources.
        try(Statement statement = conn.createStatement()) {

            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;

        } catch(SQLException e) {
            System.out.println("Create View failed: " + e.getMessage());
            return false;
        }
    }

    // 'title' is the name of the song.
    public List<SongArtist> querySongInfoView(String title) {

        try {
            // In addition to the setString method, the PreparedStatement class also has the setInt method (if you are trying to retrieve
            // an integer of course).
            // '1' specifies the position of the place holder we want to replace because there can be more than one. This is usual with JDBC
            // because the position is one-based (as opposed to Java which is zero-based).
            // setString(1, title) - refers to the first occurrence of the ' = ? '.
            // For example:
            // SELECT name, album, track FROM artist_list WHERE title = ? ORDER BY ?, ?
            // The 1 below refers to the first ? associated with title. If you wrote 2, then you'll be referring to the second occurrence of ?
            // - which is next to the ORDER BY clause. 3 will refer to the ? next to the second ?, and on and on.
            // In this case, we're replacing the first occurrence of the question mark with the title.
            querySongInfoView.setString(1, title);
            // Because PreparedStatement is a subclass of Statment, we are able to use the executeQuery method.
            // There can only be one ResultSet associated with a Statement.

            ResultSet results = querySongInfoView.executeQuery();
            // title should be not be passed as an argument (as shown above) - because the title has already being pre-compiled in the setString
            // method above.
//            ResultSet results = querySongInfoView.executeQuery(title);

            List<SongArtist> songArtists = new ArrayList<>();
            while(results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }

            return songArtists;

        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }




    private int insertArtist(String name) throws SQLException {

        // Insert name into the first '?' in the query string.
        queryArtist.setString(1, name);
        // Querying the artist table to see if it does exist.
        ResultSet results = queryArtist.executeQuery();

        // Checks whether there is an ID returned (from 'Statement.RETURN_GENERATED_KEYS' stated in the open method)..
        if(results.next()) {
            // Retrieving the id that we get from the ResultSet above.
            // getInt(1) refers to COLUMN 1.
            // if we get a number back from our first query, we know that the artist is already on file (hence we're returning the id
            // of the existing artist). We then exit the method because the artist is already on file.
            return results.getInt(1);
        }

        // Else statement is executed if the artist is not present.
        else {
            // Insert the artist id
            insertIntoArtists.setString(1, name);
            // Updates the table and sends the number of records that was affected (should be 1 as we are only inserting one record at a time).
            int affectedRows = insertIntoArtists.executeUpdate();

            // Error found while updating record.
            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }

            // At this stage, the record has been successfully updated.

            // Getting the result set that contains the generated key.
            // We only expect one key to be returned.
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()) {
                // returns the artist's _id
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {

        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        if(results.next()) {
            // ALbum is already in file and we are returning the associated id of the record.
            return results.getInt(1);
        } else {
            // Insert the album
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }

    // The commits will be made ONLY when all the SQL statements runs successfully.
    public void insertSong(String title, String artist, String album, int track) {

        try {
            // Setting setAutoCommit to false as we are setting our own transactions. By default, all sql statements are automatically
            // run as a transaction. We are turning off the autocommit behaviour of the connection. The database does that by running any
            // CRUD operation as a transaction.
            conn.setAutoCommit(false);

            // The lines of code below is used to insert an artist.
            // insertArtist will return the id of either the existing artist or the newly inserted record.
            // The next 7 lines updates the 3 TABLES.
            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            // Inserting the song into the prepared statement.
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);
            int affectedRows = insertIntoSongs.executeUpdate();
            // Checking for the row where the song was inserted.
            if(affectedRows == 1) {
                // Commiting the new song to the database. Committing changes also ends the transaction.
                conn.commit();
            } else {
                throw new SQLException("The song insert failed");
            }

//        } catch(SQLException e) {

            // We are using the generic Exception because of the occasions where the Exception caught are not SQLException. This will ensure
            // that our rollback will always be called when there is an exception.
        } catch(Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                // If there was an error, the connection will rollback to the original transaction.
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("Oh boy! Things are really bad! " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                // Returning connection to the default autocommit behaviour. This is best practice.
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }

        }
    }


}


/*
            // THIS CODE queryArtists HAS BEEN REPLACED BY THE TRY-WITH-RESOURCES WAY.

        Statement statement = null;
        ResultSet results = null;

        try {
            conn.createStatement();
            // Returns all the artist's records.
            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (results.next()){
                // Creating a new Artist object for each record.
                Artist artist = new Artist();
                artist.setId(results.getInt(COLUMN_ARTIST_ID));
                artist.setName(results.getString(COLUMN_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;
        }

        catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

        finally {
                // Closing the ResultSet.
            // Good practise to separte out the try-catch blocks
            try {
                if (results != null){
                    results.close();
                }
            }
            catch (SQLException e){
                System.out.println("ResultSet cannot be closed: " + e.getMessage());
            }

                // Closing the Statement instance.
            try {
                if (statement != null){
                    statement.close();
                }
            }
            catch (SQLException e){
                System.out.println("Statement cannot be closed: " + e.getMessage());
            }
        }
 */















