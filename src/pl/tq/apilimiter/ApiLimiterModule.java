package pl.tq.apilimiter;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import pl.tq.apilimiter.adnotations.Limit;
import pl.tq.apilimiter.examplexervice.SomeApi;
import pl.tq.apilimiter.examplexervice.SomeApiImplementation;

public class ApiLimiterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SomeApi.class).to(SomeApiImplementation.class);
        LimitManager limitManager = new LimitManager();
        limitManager.loadAllLimitClasses();
        ApiLimitationWorker apiLimitationWorker = new ApiLimitationWorker(limitManager);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Limit.class), apiLimitationWorker);
    }
}
