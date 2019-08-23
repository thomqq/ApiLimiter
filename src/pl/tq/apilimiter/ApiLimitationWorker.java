package pl.tq.apilimiter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import pl.tq.apilimiter.adnotations.Limit;
import pl.tq.apilimiter.adnotations.LimitPeriod;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ApiLimitationWorker implements MethodInterceptor {

    private final static Object lock = new Object();
    private final LimitManager limitManager;

    ApiLimitationWorker(LimitManager limitManager) {

        this.limitManager = limitManager;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        synchronized (lock) {
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
