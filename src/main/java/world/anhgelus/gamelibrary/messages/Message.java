package world.anhgelus.gamelibrary.messages;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private final String name;
    private final Map<String, String> messages;

    public Message(String name) {
        this.name = name;
        this.messages = new HashMap<>();
    }

    public void setMessage(String key, String message) {
        messages.put(key, message);
    }

    public String getMessage(String key) {
        return messages.get(key);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMessages() {
        return messages;
    }
}
