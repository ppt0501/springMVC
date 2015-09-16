package com.ppt.web.util;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;  
  
public class BeanFactoryProxyClient implements BeanFactoryPostProcessor {  
  
    private ConfigurableListableBeanFactory beanFactory;  
    private String basePackage = "com.ppt.platform.service";  
    private String baseUrl = "http://localhost:9090/server/";  
    @Override  
    public void postProcessBeanFactory(  
            ConfigurableListableBeanFactory beanFactory) throws BeansException {  
        this.beanFactory = beanFactory;  
      httpInvoker();  
//      hessian();  
//        burlap();  
    }  
    private void httpInvoker() {  
        ClassPathScanning scann = new ClassPathScanning(true);  
        scann.getClassFromPackage(basePackage);  
        List<String> classNameSet = scann.getClassNameSet();  
        List<Class<?>> clzSet = scann.getClzSet();  
        for(int i=0; i<classNameSet.size(); i++) {  
            Class<?> clz = clzSet.get(i);  
            String className = classNameSet.get(i);  
            HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();  
            factoryBean.setServiceInterface(clz);  
            factoryBean.setServiceUrl(baseUrl + className);  
            factoryBean.afterPropertiesSet();  
            beanFactory.registerSingleton(className, factoryBean.getObject());  
        }  
    }  
    private void hessian() {  
        ClassPathScanning scann = new ClassPathScanning(true);  
        scann.getClassFromPackage(basePackage);  
        List<String> classNameSet = scann.getClassNameSet();  
        List<Class<?>> clzSet = scann.getClzSet();  
        for(int i=0; i<classNameSet.size(); i++) {  
            Class<?> clz = clzSet.get(i);  
            String className = classNameSet.get(i);  
            HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();  
            factoryBean.setServiceInterface(clz);  
            factoryBean.setServiceUrl(baseUrl + className);  
            factoryBean.afterPropertiesSet();  
            beanFactory.registerSingleton(className, factoryBean.getObject());  
        }  
    }  
    private void burlap() {  
        ClassPathScanning scann = new ClassPathScanning(true);  
        scann.getClassFromPackage(basePackage);  
        List<String> classNameSet = scann.getClassNameSet();  
        List<Class<?>> clzSet = scann.getClzSet();  
        for(int i=0; i<classNameSet.size(); i++) {  
            Class<?> clz = clzSet.get(i);  
            String className = classNameSet.get(i);  
            BurlapProxyFactoryBean factoryBean = new BurlapProxyFactoryBean();  
            factoryBean.setServiceInterface(clz);  
            factoryBean.setServiceUrl(baseUrl + className);  
            factoryBean.afterPropertiesSet();  
            beanFactory.registerSingleton(className, factoryBean.getObject());  
        }  
    }  
  
}  
