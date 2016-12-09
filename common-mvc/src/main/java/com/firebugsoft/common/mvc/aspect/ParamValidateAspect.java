package com.firebugsoft.common.mvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.Errors;

import com.firebugsoft.common.mvc.json.Body;
import com.firebugsoft.common.mvc.json.Meta;

/**
 * 数据验证拦截器
 * 
 * @author felix
 */
public class ParamValidateAspect {
    
    public Object validateParam(ProceedingJoinPoint pjp) throws Throwable {
        for (Object arg : pjp.getArgs()) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    String errorField = errors.getFieldError().getField();
                    return Body.newInstance(new Meta(400, errorField));
                }
            }
        }
        return pjp.proceed();
    }
}
