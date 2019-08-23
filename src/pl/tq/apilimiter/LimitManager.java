package pl.tq.apilimiter;

import java.util.HashMap;
import java.util.List;

class LimitManager {
    private HashMap<String, LimitClass> limits = new HashMap<>();

    LimitManager() {
    }

    void addLimitIfNotAdded(String id, LimitClass limitClass) {
        if (!limits.containsKey(id)) {
            limits.put(id, limitClass);
        }
    }

    void process() {
        for (String key : limits.keySet()) {
            limits.get(key).process();
        }
    }

    void addLimitIfNotAdded(String id, List<LimitClass> limitClasses) {
        for (LimitClass limit : limitClasses) {
            addLimitIfNotAdded(id, limit);
        }
    }

    void loadAllLimitClasses() {
    }

    void saveAllLimitClasses() {
    }
}
