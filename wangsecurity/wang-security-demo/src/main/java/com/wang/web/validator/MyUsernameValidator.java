package com.wang.web.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 21:32
 */
public class MyUsernameValidator implements ConstraintValidator<MyConstraint,String> {

//    @Autowired
//    private UserInfoService userInfoService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("init myConstraint.....");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(s.trim())){
            return false;
        } else {
            return true;
        }
    }
}
