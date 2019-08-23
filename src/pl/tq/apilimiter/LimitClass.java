package pl.tq.apilimiter;

import pl.tq.apilimiter.adnotations.LimitPeriod;

public abstract class LimitClass {
    protected final String counterName;
    protected final Long amount;
    protected final LimitPeriod period;

    public LimitClass(String counterName, Long amount, LimitPeriod period) {
        this.counterName = counterName;
        this.amount = amount;
        this.period = period;
    }

    abstract void process();
}
