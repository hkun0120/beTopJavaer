package com.msb.learning.thinkinginspringboot.handler;

import com.msb.learning.thinkinginspringboot.annotation.Hidden;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: H.K
 * @create: 2020-12-01 16:53
 */
@Aspect
@Component
public class LogAspectHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 定义一个切面，拦截com.itcodai.course09.controller包和子包下的所有方法
     */
//    @Pointcut("execution(* com.clic.thinkinginspringboot.controller..*.*(..))")
//    public void pointCut() {}
//
//
    /**
     * 对具有@GetMapping注解的方法做切面，可以如下定义切面
     */
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void annotationCut() {}


    /**
     * 在上面定义的切面方法之前执行该方法
     * @param joinPoint jointPoint
     */
//    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("====doBefore方法进入了====");

        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        logger.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 在上面定义的切面方法之后执行该方法
     * @param joinPoint jointPoint
     */
//    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {

        logger.info("====doAfter方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);
    }

    @Pointcut("@annotation(com.msb.learning.thinkinginspringboot.annotation.Sensitive)")
    public void annotationCut1() {}

    @Around("annotationCut1()")
    public void doAfter1(JoinPoint joinPoint){
        logger.info("====doAfter1方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);
    }

//    @Around("annotationCut1()")
    public Object aroundReturning(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取dao层接口参数注解 start
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                //方法参数包含注解,进行加密
                if (annotation instanceof Hidden) {
                    logger.info("sssssssss"+args[i]);
                }
            }
        }
        //获取dao层接口参数注解 end

//        //获取参数实体类注解
//        CryptPojoUtils.encryptFields(args);
//        //执行方法
//        Object proceed = joinPoint.proceed(args);
//        if (null == proceed) {
//            return null;
//        }
//        //解密
//        CryptPojoUtils.decryptFieldOrList(proceed);
//        return proceed;
        return null;
    }

}
