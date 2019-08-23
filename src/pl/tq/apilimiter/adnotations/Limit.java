package pl.tq.apilimiter.adnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) @Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Limit {
    long amount() default 0;
    LimitPeriod period() default LimitPeriod.DAY;
    String name() default "";

}
