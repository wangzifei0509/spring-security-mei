package com.wang.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 21:28
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyUsernameValidator.class)
public @interface MyConstraint {
    String message() ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
