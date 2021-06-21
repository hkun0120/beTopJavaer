package com.msb.learning.thinkinginspringboot.handler;

import com.msb.learning.thinkinginspringboot.annotation.Hidden;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: SecretAOPController
 * @description: 切面加密解密
 * @author: chenhx
 * @create: 2019-12-05 13:43
 **/
@Aspect
@Component
public class SecretAOPController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    // 是否进行加密解密，通过配置文件注入（不配置默认为true）
    @Value("${isSecret:true}")
    boolean isSecret;

    // 定义切点,使用了@Secret注解的类 或 使用了@Secret注解的方法
    @Pointcut("@annotation(com.msb.learning.thinkinginspringboot.annotation.Sensitive)")
    public void pointcut(){}

    // 环绕切面
//    @Around("pointcut()")
    public Object around1(ProceedingJoinPoint point){




        String result = null;

        // 获取被代理方法参数
        Object[] args = point.getArgs();

        // 获取被代理对象
        Object target = point.getTarget();

        // 获取通知签名
        MethodSignature signature = (MethodSignature)point.getSignature();

        try {
            // 获取被代理方法
            Method pointMethod = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            // 获取被代理方法上面的注解@Secret
//            Secret2 secret = pointMethod.getAnnotation(Secret2.class);
//            // 被代理方法上没有，则说明@Secret注解在被代理类上
//            if(secret==null){
//                secret = target.getClass().getAnnotation(Secret2.class);
//            }
//
//            if(secret!=null){
//                // 获取注解上声明的加解密类
//                Class clazz = secret.value();
//                // 获取注解上声明的加密参数名
//                String encryptStrName = secret.encryptStrName();
//
//                for (int i = 0; i < args.length; i++) {
//                    // 如果是clazz类型则说明使用了加密字符串encryptStr传递的加密参数
//                    if(clazz.isInstance(args[i])){
//                        Object cast = clazz.cast(args[i]);      //将args[i]转换为clazz表示的类对象
//                        // 通过反射，执行getEncryptStr()方法，获取加密数据
//                        Method method = clazz.getMethod(getMethedName(encryptStrName));
//                        // 执行方法，获取加密数据
//                        String encryptStr = (String) method.invoke(cast);
//                        // 加密字符串是否为空
//                        if(StringUtils.isNotBlank(encryptStr)){
//                            // 解密
//                            String json = (String) AESUtils.decrypt(encryptStr);
//                            // 转换vo
//                            args[i] = JSON.parseObject(json, (Type) args[i].getClass());
//                        }
//                    }
//                    // 其他类型，比如基本数据类型、包装类型就不使用加密解密了
//                }
//            }
//
//            // 执行请求
//            result = (ResultVO) point.proceed(args);

            // 判断配置是否需要返回加密
            if(isSecret){
                // 获取返回值json字符串
//                String jsonString = JSON.toJSONString(result.getData());
                // 加密
//                String s = (String) AESUtils.encrypt(jsonString);
//                result.setData(s);
            }

        } catch (NoSuchMethodException e) {
//            log.error("@Secret注解指定的类没有字段:encryptStr,或encryptStrName参数字段不存在");
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    // 转化方法名
    private String getMethedName(String name){
        String first = name.substring(0,1);
        String last = name.substring(1);
//        first = StringUtils.upperCase(first);
        return "get" + first + last;
    }


    @Pointcut("@annotation(com.msb.learning.thinkinginspringboot.annotation.Sensitive)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            Object requestObj = joinPoint.getArgs();
             requestObj = joinPoint.getArgs()[0];
            handleEncrypt(requestObj);
            responseObj = joinPoint.proceed();
            handleDecrypt(responseObj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            log.error("SecureFieldAop处理出现异常{}", e);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("SecureFieldAop处理出现异常{}", throwable);
        }
        return responseObj;
    }

    /**
     * 处理加密
     *
     * @param requestObj
     */
    private void handleEncrypt(Object requestObj) throws IllegalAccessException {
        if (Objects.isNull(requestObj)) {
            return;
        }
        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(Hidden.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String plaintextValue = (String) field.get(requestObj);
                String encryptValue = "AseUtil.encrypt(plaintextValue, secretKey)";
                field.set(requestObj, encryptValue);
            }
        }
    }


    /**
     * 处理解密
     *
     * @param responseObj
     */
    private Object handleDecrypt(Object responseObj) throws IllegalAccessException {
        if (Objects.isNull(responseObj)) {
            return null;
        }

        Field[] fields = responseObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(Hidden.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String encryptValue = (String) field.get(responseObj);
                String plaintextValue = "AseUtil.decrypt(encryptValue, secretKey)";
                field.set(responseObj, plaintextValue);
            }
        }
        return responseObj;
    }
}
