package world.anhgelus.gamelibrary.database.sql;

/**
 * Database credentials
 * @param type Database type
 * @param host Host of the database (e.g: localhost)
 * @param port Port of the database
 * @param database Database name
 * @param username Username of the database
 * @param password Password of the database
 */
public record DatabaseCredentials(DatabaseType type, String host, int port, String database, String username, String password) {
}
