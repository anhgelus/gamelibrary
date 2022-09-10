package world.anhgelus.gamelibrary.database;

import java.sql.*;
import java.util.*;

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

    /**
     * Execute a query
     * @param table Table to execute the query
     * @param columns Columns to execute the query
     * @param queryAll Query everything
     * @return Map of the query ID -> (String = Column name, Object = Column value)
     * @throws SQLException Exception
     */
    public Map<Integer, Map<String, Object>> query(String table, String[] columns, boolean queryAll) throws SQLException {
        final Statement statement = connection.createStatement();

        ResultSet resultSet;
        if (queryAll) {
            resultSet = statement.executeQuery("SELECT * FROM "+table+"");
        } else {
            final StringBuilder sb = new StringBuilder();
            for (String column : columns) {
                sb.append(column).append(",");
            }
            resultSet = statement.executeQuery("SELECT "+sb.toString()+" FROM "+table+"");
        }

        final Map<Integer, Map<String, Object>> objects = new HashMap();

        while (resultSet.next()) {
            final Map<String, Object> object = new HashMap();
            for (String column : columns) {
                object.put(column, resultSet.getObject(column));
            }
            objects.put(resultSet.getRow(), object);
        }
        return objects;
    }

    /**
     * Execute a query
     * @param table Table to execute the query
     * @param columns Columns to execute the query
     * @return Map of the query ID -> (String = Column name, Object = Column value)
     * @throws SQLException Exception
     */
    public Map<Integer, Map<String, Object>> query(String table, String[] columns) throws SQLException {
        return query(table, columns, false);
    }

    public Connection getConnection() {
        return connection;
    }
}
