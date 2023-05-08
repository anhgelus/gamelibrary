package world.anhgelus.gamelibrary.util;

import java.util.HashMap;
import java.util.Map;

public class Vault<T> {
    private Map<String, T> vault;

    public Vault() {
        this.vault = new HashMap<>();
    }

    /**
     * Set the vault
     * @param vault The vault
     */
    public void setVault(Map<String, T> vault) {
        this.vault = vault;
    }

    /**
     * Get the vault
     * @return The vault
     */
    public Map<String, T> getVault() {
        return vault;
    }

    /**
     * Get the value of the key
     * @param key The key of the vault
     * @return The value of the key
     */
    public T get(String key) {
        return vault.get(key);
    }

    /**
     * Check if the vault contains the key
     * @param key The key
     * @return True if it contains, else false
     */
    public boolean contains(String key) {
        return vault.containsKey(key);
    }

    /**
     * Add a value to the vault
     * @param key Key of the value
     * @param value Value of the key
     */
    public void put(String key, T value) {
        vault.put(key, value);
    }
}
