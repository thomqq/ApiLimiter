package pl.tq.apilimiter;

import pl.tq.apilimiter.examplexervice.ApiFactory;
import pl.tq.apilimiter.examplexervice.SomeApi;

public class Main {

    public static void main(String[] args) {
        SomeApi api = ApiFactory.createApiPort();
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
        System.out.println(api.getMp3ForSentence("roki", 1));
    }
}
