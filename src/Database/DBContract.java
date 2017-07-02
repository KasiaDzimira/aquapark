package Database;

/**
 * Contains database properties
 */
public interface DBContract {

    /**
     * URL to the database
     */
    public static final String HOST = "jdbc:postgresql://localhost:5432/";

    /**
     * name of the database
     */
    public static final String DB_NAME = "aquapark";

    /**
     * username for the database
     */
    public static final String USERNAME = "postgres";

    /**
     * password for the username provided above
     */
    public static final String PASSWORD = "admin";
}
