package pl.tq.apilimiter.limits;

import pl.tq.apilimiter.annotations.LimitPeriod;

public class CallLimitClass extends LimitClass {

    private long currentAmount;

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