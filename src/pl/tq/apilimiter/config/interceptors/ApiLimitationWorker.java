package pl.tq.apilimiter.config.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import pl.tq.apilimiter.limits.CallLimitClass;
import pl.tq.apilimiter.limits.LimitClass;
import pl.tq.apilimiter.limits.LimitManager;
import pl.tq.apilimiter.annotations.Limit;
import pl.tq.apilimiter.annotations.LimitPeriod;

import javax.inject.Inject;
import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ApiLimitationWorker implements MethodInterceptor {

    private final static Object lock = new Object();
    private final Provider<LimitManager> limitManagerProvider;

    @Inject
    public ApiLimitationWorker(Provider<LimitManager> limitManagerProvider) {
        this.limitManagerProvider = limitManagerProvider;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        synchronized (lock) {
            LimitManager limitManager = limitManagerProvider.get();
            String limiterName = methodInvocation.getClass().getSimpleName() + "." + methodInvocation.getMethod().getName();
            LimitClass limit = prepareMethodLimit(methodInvocation);
            limitManager.addLimitIfNotAdded(limiterName, limit);
            List<LimitClass> limitClasses = prepareParametresLimits(methodInvocation);
            limitManager.addLimitIfNotAdded(limiterName, limitClasses);

            limitManager.process();
        }

        return methodInvocation.proceed();
    }

    private LimitClass prepareMethodLimit(MethodInvocation methodInvocation) {
        Limit limitAnnotation = methodInvocation.getMethod().getAnnotation(Limit.class);

        Long amount = limitAnnotation.amount();
        LimitPeriod period = limitAnnotation.period();
        String counterName = limitAnnotation.name();

        return new CallLimitClass(counterName, amount, period);
    }

    private List<LimitClass> prepareParametresLimits(MethodInvocation methodInvocation) {
        Annotation[][] parameterAnnotations = methodInvocation.getMethod().getParameterAnnotations();
        return new ArrayList<>();
    }

}
