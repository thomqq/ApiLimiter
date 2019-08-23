package pl.tq.apilimiter.limits;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;

@Singleton
public
class LimitManager {
    private HashMap<String, LimitClass> limits = new HashMap<>();

    @Inject
    LimitManager() {
    }

    public void addLimitIfNotAdded(String id, LimitClass limitClass) {
        if (!limits.containsKey(id)) {
            limits.put(id, limitClass);
        }
    }

    public void process() {
        for (String key : limits.keySet()) {
            limits.get(key).process();
        }
    }

    public void addLimitIfNotAdded(String id, List<LimitClass> limitClasses) {
        for (LimitClass limit : limitClasses) {
            addLimitIfNotAdded(id, limit);
        }
    }

    public void loadAllLimitClasses() {
    }

    public void saveAllLimitClasses() {
    }
}
