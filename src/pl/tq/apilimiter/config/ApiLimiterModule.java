package pl.tq.apilimiter.config;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import pl.tq.apilimiter.config.interceptors.ApiLimitationWorker;
import pl.tq.apilimiter.limits.LimitManager;
import pl.tq.apilimiter.annotations.Limit;
import pl.tq.apilimiter.examplexervice.SomeApi;
import pl.tq.apilimiter.examplexervice.SomeApiImplementation;

public class ApiLimiterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SomeApi.class).to(SomeApiImplementation.class);
        bind(ApiLimitationWorker.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Limit.class), new ApiLimitationWorker(getProvider(LimitManager.class)));
    }
}
