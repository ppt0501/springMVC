package com.ppt.web.util;

import java.util.ArrayList;  
import java.util.List;  
  
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;  
import org.springframework.core.io.Resource;  
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;  
import org.springframework.core.io.support.ResourcePatternResolver;  
import org.springframework.core.type.ClassMetadata;  
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;  
import org.springframework.core.type.classreading.MetadataReader;  
import org.springframework.core.type.classreading.MetadataReaderFactory;  
  
  
public class ClassPathScanning extends ClassPathScanningCandidateComponentProvider {  
  
    private List<String> classNameSet = new ArrayList<String>(20);  
    private List<Class<?>> clzSet = new ArrayList<Class<?>>(20);  
    public ClassPathScanning(boolean useDefaultFilters) {  
        super(useDefaultFilters);  
    }  
    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";  
    private String resourcePattern = DEFAULT_RESOURCE_PATTERN;  
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();  
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);  
      
    public void getClassFromPackage(String basePackage) {  
        try {  
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +  
                    resolveBasePackage(basePackage) + "/" + this.resourcePattern;  
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);  
            for(Resource r : resources) {  
                if(r.isReadable()) {  
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(r);  
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();  
                    if(!classMetadata.isIndependent()) {  
                        continue;  
                    }  
                    String className = classMetadata.getClassName();  
                    int index = className.lastIndexOf(".");  
                    if(index > 0) {  
                        String name = className.substring(index+1);  
                        name = name.substring(0, 1).toLowerCase() + name.substring(1);  
                        classNameSet.add(name);  
                        clzSet.add(Class.forName(className));  
                    }  
                }  
            }  
        } catch(Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public List<String> getClassNameSet() {  
        return classNameSet;  
    }  
  
    public List<Class<?>> getClzSet() {  
        return clzSet;  
    }  
      
}  
