package com.gamerduck.commons.general;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.reflect.ClassPath;

public class Classes {

    public static List<Class<?>> getClassesInPackage(String packageName, ClassLoader classLoader) {
    	List<Class<?>> classes = new ArrayList<Class<?>>();
    	try {
			ClassPath.from(classLoader).getTopLevelClassesRecursive(packageName).forEach(ci -> {
				try {classes.add(Class.forName(ci.getName()));
				} catch (ClassNotFoundException e) {e.printStackTrace();}
			});
		} catch (IOException e) {e.printStackTrace();}
    	return classes;
    } 
	
    public static List<Class<?>> getClassesWithAnnotation(String packageName, ClassLoader loader, Class<? extends Annotation> annon) {
    	return getClassesInPackage(packageName, loader).stream().filter(clazz -> clazz.isAnnotationPresent(annon)).collect(Collectors.toList());
    } 
	
    public static List<Class<?>> getClassesThatExtend(String packageName, ClassLoader loader, Class<?> clazz) {
    	return getClassesInPackage(packageName, loader).stream().filter(c -> c.isInstance(clazz)).collect(Collectors.toList());
    } 
    
    public static List<Class<?>> getClassesThatImplement(String packageName, ClassLoader loader, Class<?> clazz) {
    	return getClassesInPackage(packageName, loader).stream().filter(c -> c.isAssignableFrom(clazz)).collect(Collectors.toList());
    } 
}
