package world.anhgelus.gamelibrary.util;

import java.util.Map;

public class Vault {
    private static Map<String, Object> vault;

    /**
     * Set the vault
     * @param vault The vault
     */
    public static void setVault(Map<String, Object> vault) {
        Vault.vault = vault;
    }

    /**
     * Get the vault
     * @return The vault
     */
    public static Map<String, Object> getVault() {
        return vault;
    }

    /**
     * Get the value of the key
     * @param key The key of the vault
     * @return The value of the key
     */
    public static Object get(String key) {
        return vault.get(key);
    }

    /**
     * Check if the vault contains the key
     * @param key The key
     * @return True if it contains, else false
     */
    public static boolean contains(String key) {
        return vault.containsKey(key);
    }

    /**
     * Add a value to the vault
     * @param key Key of the value
     * @param value Value of the key
     */
    public static void put(String key, Object value) {
        vault.put(key, value);
    }
}
