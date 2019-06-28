package com.dubbo.api.aop;

import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.constant.CommonConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:14:44
 * Modify date: 2019-06-28:14:44
 */
@Aspect
@Component
public class AuthPermissionAspect {

    @Pointcut("@annotation(authPermission)")
    public void doAuthPermission(AuthPermission authPermission){}


    public Object deBefore(ProceedingJoinPoint pjp, AuthPermission authPermission) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String[] roles = authPermission.roles();
        if (roles == null || roles.length == 0){
            //验证登录token
            return pjp.proceed();
        }else {
            String role = request.getHeader("token");
            for (String str : roles){
                if (str.equals(role)){
                    return pjp.proceed();
                }
            }
            return new ErrorResponse(CommonConstant.AUTH_FAILED);
        }

    }

}
