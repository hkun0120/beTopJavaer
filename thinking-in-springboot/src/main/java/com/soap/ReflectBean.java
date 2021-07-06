package com.soap;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: H.K
 * @create: 2021-07-06 15:59
 */
public class ReflectBean {
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     */
    public static Map<String, String> beanConvertMap(Object bean){
        Class<? extends Object> cls = bean.getClass();
        Map<String, String> returnMap = new HashMap<String, String>();
        Field[] fields = cls.getDeclaredFields();
        for(int i = 0 ; i < fields.length ; i++)
        {
            PropertyDescriptor descriptor=BeanUtils.getPropertyDescriptor(cls, fields[i].getName());
            Method readMethod = descriptor.getReadMethod();
            try {
                String result = readMethod.invoke(bean, new Object[0])==null?"": readMethod.invoke(bean, new Object[0]).toString();
                if (!"".equals(result) && result != null) {
                    returnMap.put(fields[i].getName(), result);
                }
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
        return returnMap;
    }
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param obj 要转化的对象
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     */
    public static  Object mapConvertBean(Map<String, String> maps,Object obj){
        Class cls=obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {

            if (maps.containsKey(fields[i].getName())) {
                Object values = maps.get(fields[i].getName());

                Object[] argss = new Object[1];
                argss[0] = (String) values;

                PropertyDescriptor descriptor= BeanUtils.getPropertyDescriptor(cls, fields[i].getName());
                try {
                    descriptor.getWriteMethod().invoke(obj, values);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }


}
