package com.msb.learning.thinkinginspringboot.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: H.K
 * @create: 2020-12-01 17:23
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Hidden {
}
