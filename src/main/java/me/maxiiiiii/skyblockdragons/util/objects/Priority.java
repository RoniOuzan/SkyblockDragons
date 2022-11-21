package me.maxiiiiii.skyblockdragons.util.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {
    int DEFAULT = 5;

    int level() default DEFAULT;
}
