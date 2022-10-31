package world.anhgelus.gamelibrary.database.sql;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private final static Map<String, Database> DATABASES = new HashMap<>();

    public static void addDatabase(String name, Database database) {
        DATABASES.put(name, database);
    }

    public static Database getDatabase(String name) {
        return DATABASES.get(name);
    }

    public static void removeDatabase(String name) {
        DATABASES.remove(name);
    }

    /**
     * Create a database and register it with its name
     * @param credentials Database credentials
     * @return Database created
     */
    public static Database createDatabase(DatabaseCredentials credentials) {
        final Database database = new Database(credentials);
        addDatabase(credentials.database(), database);
        return database;
    }
}
