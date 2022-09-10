package world.anhgelus.gamelibrary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection connection;
    private final DatabaseCredentials credentials;

    public Database(DatabaseCredentials credentials) {
        this.credentials = credentials;
    }

    public Database(DatabaseType type, String host, int port, String database, String username, String password) {
        credentials = new DatabaseCredentials(type, host, port, database, username, password);
    }

    public String generateHost() {
        return credentials.type().getJdbc() + "://" + credentials.host() + ":" + credentials.port() + "/" + credentials.database();
    }

    public Connection connect() throws SQLException {
        return connection = DriverManager.getConnection(generateHost(), credentials.username(), credentials.password());
    }

    public void close() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is null");
        }
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
