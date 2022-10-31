package world.anhgelus.gamelibrary.database.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
    public final RedisCredentials redisCredentials;

    private JedisPool pool;

    public Redis(RedisCredentials redisCredentials) {
        this.redisCredentials = redisCredentials;
    }

    /**
     * Connect to the pool
     * @return Redis pool
     */
    public JedisPool connect() {
        return pool = new JedisPool(redisCredentials.host(), redisCredentials.port());
    }

    /**
     * Close the pool
     * @throws RedisException If the pool is null
     */
    public void close() throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        if (!pool.isClosed()) {
            pool.close();
        }
    }

    /**
     * Set a resource
     * @throws RedisException If the pool is null
     */
    public void set(String key, String value) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        }
    }

    /**
     * Get a resource
     * @param key Key of the resource
     * @return Value of the resource
     * @throws RedisException If the pool is null
     */
    public String get(String key) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * Delete a resource
     * @param key Key of the resource
     * @throws RedisException If the pool is null
     */
    public void delete(String key) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            jedis.del(key);
        }
    }

    /**
     * Hset a resource
     * @param key Key of the resource
     * @param subkey Subkey of the resource
     * @param value Value of the resource
     * @throws RedisException If the pool is null
     */
    public void hset(String key, String subkey, String value) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            jedis.hset(key, subkey, value);
        }
    }

    /**
     * Hget a resource
     * @param key Key of the resource
     * @param subkey Subkey of the resource
     * @return Value of the resource
     * @throws RedisException If the pool is null
     */
    public String hget(String key, String subkey) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            return jedis.hget(key, subkey);
        }
    }

    /**
     * Hdel a resource
     * @param key Key of the resource
     * @param subkey Subkey of the resource
     * @throws RedisException If the pool is null
     */
    public void hdel(String key, String subkey) throws RedisException {
        if (pool == null) {
            throw new RedisException("Pool is null");
        }
        try (Jedis jedis = pool.getResource()) {
            jedis.hdel(key, subkey);
        }
    }
}
