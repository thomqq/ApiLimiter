package pl.tq.apilimiter.examplexervice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.tq.apilimiter.ApiLimiterModule;

import javax.inject.Inject;

public class ApiFactory {
    public static SomeApi createApiPort() {
        Injector injector = Guice.createInjector(new ApiLimiterModule());
        SomeApi someApi = injector.getInstance(SomeApi.class);
        return someApi;
    }
}
