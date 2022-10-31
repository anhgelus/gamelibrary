package world.anhgelus.gamelibrary.database.sql;

public enum DatabaseType {
    MYSQL("mysql","jdbc:mysql"),
    MARIA_DB("mariadb","jdbc:mariadb"),
    POSTGRESQL("postgresql","jdbc:pgsql");

    public final String name;
    public final String jdbc;

    DatabaseType(String name, String jdbc) {
        this.name = name;
        this.jdbc = jdbc;
    }

    public String getName() {
        return name;
    }

    public String getJdbc() {
        return jdbc;
    }
}
