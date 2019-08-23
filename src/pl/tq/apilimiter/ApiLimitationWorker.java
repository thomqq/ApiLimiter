package pl.tq.apilimiter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import pl.tq.apilimiter.adnotations.Limit;
import pl.tq.apilimiter.adnotations.LimitPeriod;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class ApiLimitationWorker implements MethodInterceptor {

    private static Object lock = new Object();
    private static HashMap<String, LimitClass> limits = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        synchronized (lock) {
            analiseAndInitLimits(methodInvocation);
            analiseAndInitParametersLimits(methodInvocation);
            processLimits();
            saveAllLimits();
        }

        return methodInvocation.proceed();
    }

    private void processLimits() {
        for (String key : limits.keySet()) {
            limits.get(key).process();
        }
    }

    private void analiseAndInitLimits(MethodInvocation methodInvocation) {
        Limit limitAnnotation = methodInvocation.getMethod().getAnnotation(Limit.class);

        Long amount = limitAnnotation.amount();
        LimitPeriod period = limitAnnotation.period();
        String counterName = createCounterName(methodInvocation, limitAnnotation);

        LimitClass limitClass = limits.get(counterName);
        if (limitClass == null) {
            limitClass = new CallLimitClass(counterName, amount, period);
            limits.put(counterName, limitClass);
        }
    }

    private void analiseAndInitParametersLimits(MethodInvocation methodInvocation) {
        Annotation[][] parameterAnnotations = methodInvocation.getMethod().getParameterAnnotations();
    }

    private String createCounterName(MethodInvocation methodInvocation, Limit limitAnnotation) {
        String counterName = limitAnnotation.name();
        if (counterName.isEmpty()) {
            counterName = methodInvocation.getClass().getSimpleName() + "." + methodInvocation.getMethod().getName();
        }
        return counterName;
    }

    public void loadAllLimitClasses() {

    }

    private void saveAllLimits() {

    }

}
