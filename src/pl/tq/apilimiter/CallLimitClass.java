package pl.tq.apilimiter;

import pl.tq.apilimiter.adnotations.LimitPeriod;

public class CallLimitClass extends LimitClass {

    long currentAmount = 0;

    public CallLimitClass(String counterName, Long amount, LimitPeriod period) {
        super(counterName, amount, period);
        currentAmount = amount;
    }

    @Override
    void process() {
        if (amount < 1) {
            return;
        }

        if (--currentAmount < 0) {
            throw new IllegalStateException(
                    counterName + ": too many calls"
            );
        }
    }

}