package pl.tq.apilimiter.limits;

import pl.tq.apilimiter.annotations.LimitPeriod;

public abstract class LimitClass {
    final String counterName;
    final Long amount;
    final LimitPeriod period;

    LimitClass(String counterName, Long amount, LimitPeriod period) {
        this.counterName = counterName;
        this.amount = amount;
        this.period = period;
    }

    abstract void process();
}
