package com.gamerduck.commons.general;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.reflect.ClassPath;
/**
 * This class provides a few methods for reflection
 * 
 * @author GamerDuck123
 *
 */
public class Classes {

	/**
	 * Find all classes in a package
	 *
	 * @param packageName The package path
	 * @param classLoader The class loader
	 * @return List of all of the classes in the package stated above
	 */
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

	/**
	 * Find all classes in a package with a certain annotation
	 *
	 * @param packageName The package path
	 * @param classLoader The class loader
	 * @param annon The annotation to look for
	 * @return List of all of the classes in the package with the annotation
	 */
    public static List<Class<?>> getClassesWithAnnotation(String packageName, ClassLoader loader, Class<? extends Annotation> annon) {
    	return getClassesInPackage(packageName, loader).stream().filter(clazz -> clazz.isAnnotationPresent(annon)).collect(Collectors.toList());
    } 


	/**
	 * Find all classes in a package that extend a certain class
	 *
	 * @param packageName The package path
	 * @param classLoader The class loader
	 * @param clazz The class that they need to extend
	 * @return List of all of the classes in the package that extend a certain class
	 */
    public static List<Class<?>> getClassesThatExtend(String packageName, ClassLoader loader, Class<?> clazz) {
    	return getClassesInPackage(packageName, loader).stream().filter(c -> c.isInstance(clazz)).collect(Collectors.toList());
    } 

	/**
	 * Find all classes in a package that implement a certain class
	 *
	 * @param packageName The package path
	 * @param classLoader The class loader
	 * @param clazz The class that they need to implement
	 * @return List of all of the classes in the package that implement a certain class
	 */
    public static List<Class<?>> getClassesThatImplement(String packageName, ClassLoader loader, Class<?> clazz) {
    	return getClassesInPackage(packageName, loader).stream().filter(c -> c.isAssignableFrom(clazz)).collect(Collectors.toList());
    } 
}
