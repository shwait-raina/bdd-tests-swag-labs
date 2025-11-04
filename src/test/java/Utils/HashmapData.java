package Utils;

import java.util.HashMap;
import java.util.Map;

public class HashmapData {

    // Step 1: Create a private static instance (only one allowed)
    private static HashmapData instance;

    // Step 2: Create a Map to store your key-value data
    private Map<String, Object> dataMap;

    // Step 3: Private constructor to prevent creating objects directly
    private HashmapData() {
        dataMap = new HashMap<>();
    }

    // Step 4: Public static method to get the single instance
    public static synchronized HashmapData getInstance() {
        if (instance == null) {
            instance = new HashmapData();
        }
        return instance;
    }

    // Step 5: Methods to interact with the HashMap
    public void put(String key, Object value) {
        dataMap.put(key, value);
    }

    public Object get(String key) {
        return dataMap.get(key);
    }

    public void clear() {
        dataMap.clear();
    }
}

