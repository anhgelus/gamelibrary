package world.anhgelus.gamelibrary.database.redis;

/**
 * Redis credentials
 * @param host Host of the redis server (e.g: localhost)
 * @param port Port of the redis server
 */
public record RedisCredentials(String host, int port) {
}
