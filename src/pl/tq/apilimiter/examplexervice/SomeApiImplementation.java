package pl.tq.apilimiter.examplexervice;


import pl.tq.apilimiter.adnotations.Limit;
import pl.tq.apilimiter.adnotations.LimitPeriod;

public class SomeApiImplementation implements SomeApi {

    @Override
    @Limit(amount = 3, period = LimitPeriod.DAY, name = "jakies")
    public String getMp3ForSentence(@Limit(amount = 10, period = LimitPeriod.MONTH) String sentence, int something) {
        return "HURA";
    }

}
