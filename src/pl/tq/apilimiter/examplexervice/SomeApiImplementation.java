package pl.tq.apilimiter.examplexervice;


import pl.tq.apilimiter.annotations.Limit;
import pl.tq.apilimiter.annotations.LimitPeriod;

public class SomeApiImplementation implements SomeApi {

    @Override
    @Limit(amount = 3, period = LimitPeriod.DAY, name = "some_name")
    public String getMp3ForSentence(@Limit(amount = 10, period = LimitPeriod.MONTH) String sentence, int something) {
        return sentence;
    }

}
