package com.ppt.util;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;  
  
public class BeanFactoryProxyServer implements BeanFactoryPostProcessor,ApplicationContextAware {  
  
    private ApplicationContext applicationContext;  
    private ConfigurableListableBeanFactory beanFactory;  
    private String basePackage = "com.ppt.platform.service";  
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
            HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();  
            exporter.setService(this.applicationContext.getBean(className));  
            exporter.setServiceInterface(clz);  
            exporter.afterPropertiesSet();  
            beanFactory.registerSingleton("/" + className, exporter);  
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
            HessianServiceExporter exporter = new HessianServiceExporter();  
            exporter.setService(this.applicationContext.getBean(className));  
            exporter.setServiceInterface(clz);  
            exporter.afterPropertiesSet();  
            beanFactory.registerSingleton("/" + className, exporter);  
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
            BurlapServiceExporter exporter = new BurlapServiceExporter();  
            exporter.setService(this.applicationContext.getBean(className));  
            exporter.setServiceInterface(clz);  
            exporter.afterPropertiesSet();  
            beanFactory.registerSingleton("/" + className, exporter);  
        }  
    }  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        this.applicationContext = applicationContext;  
          
    }  
}  
