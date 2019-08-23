package pl.tq.apilimiter.examplexervice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import pl.tq.apilimiter.config.ApiLimiterModule;

public class ApiFactory {
    public static SomeApi createApiPort() {
        Injector injector = Guice.createInjector(new ApiLimiterModule());
        return injector.getInstance(SomeApi.class);
    }
}
