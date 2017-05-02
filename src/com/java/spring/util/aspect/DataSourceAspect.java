package com.java.spring.util.aspect;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.java.spring.util.dynamicDataSource.DataSource;
import com.java.spring.util.dynamicDataSource.DynamicDataSourceHolder;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年4月14日 下午4:24:36
* 类说明
*/
public class DataSourceAspect {
	  //@Pointcut("execution(* com.apc.cms.service.*.*(..))")  
    public void pointCut(){};  
    
  //  @Before(value = "pointCut()")
     public void before(JoinPoint point)
        {
            Object target = point.getTarget();
            System.out.println(target.toString());
            String method = point.getSignature().getName();
            System.out.println(method);
            Class<?>[] classz = target.getClass().getInterfaces();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                    .getMethod().getParameterTypes();
            try {
                Method m = classz[0].getMethod(method, parameterTypes);
                System.out.println(m.getName());
                if (m != null && m.isAnnotationPresent(DataSource.class)) {
                    DataSource data = m.getAnnotation(DataSource.class);
                    //加载已经在内存中数据源，
                    DynamicDataSourceHolder.putDataSource(data.value());
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
