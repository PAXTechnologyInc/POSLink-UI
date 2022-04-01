package com.pax.us.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kim.L 4/1/22
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    Class<?> type();
}
