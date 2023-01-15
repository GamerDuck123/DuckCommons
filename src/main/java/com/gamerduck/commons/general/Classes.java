package com.gamerduck.commons.general;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides a few methods for reflection
 *
 * @author GamerDuck123
 */
public class Classes {
    /**
     * Find all classes in a package
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @return List of all of the classes in the package stated above
     */
    public static List<Class<?>> getClassesInPackage(String packageName, ClassLoader loader) {
        List<Class<?>> classes = Lists.newArrayList();
        try {
            ClassPath.from(loader).getTopLevelClassesRecursive(packageName).forEach(ci -> {
                try {
                    classes.add(Class.forName(ci.getName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * Find all classes in a package with a certain annotation
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @param annon       The annotation to look for
     * @return List of all of the classes in the package with the annotation
     */
    public static List<Class<?>> getClassesWithAnnotation(String packageName, ClassLoader loader, Class<? extends Annotation> annon) {
        return getClassesInPackage(packageName, loader).stream().filter(clazz -> clazz.isAnnotationPresent(annon)).collect(Collectors.toList());
    }


    /**
     * Find all classes in a package that extend a certain class
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @param clazz       The class that they need to extend
     * @return List of all of the classes in the package that extend a certain class
     */
    public static <T> List<T> getClassesThatExtend(String packageName, ClassLoader loader, Class<T> clazz) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isAssignableFrom(clazz))
                .map(c -> {
                    try {
                        return (T) c.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }

    /**
     * Find all classes in a package that implement a certain class
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @param clazz       The class that they need to implement
     * @return List of all of the classes in the package that implement a certain class
     */
    public static <T> List<T> getClassesThatImplement(String packageName, ClassLoader loader, Class<T> clazz) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isAssignableFrom(clazz))
                .map(c -> {
                    try {
                        return (T) c.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }

    /**
     * Find all classes in a package that are interfaces
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @return List of all of the classes in the package that are interfaces
     */
    public static List<Class<?>> getClassesThatAreInterfaces(String packageName, ClassLoader loader) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isInterface()).collect(Collectors.toList());
    }

    /**
     * Find all classes in a package that are enums
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @return List of all of the classes in the package that are enums
     */
    public static List<Class<?>> getClassesThatAreEnums(String packageName, ClassLoader loader) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isEnum()).collect(Collectors.toList());
    }

    /**
     * Find all classes in a package that are annotations
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @return List of all of the classes in the package that are annotations
     */
    public static List<Class<?>> getClassesThatAreAnnotations(String packageName, ClassLoader loader) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isAnnotation()).collect(Collectors.toList());
    }

    /**
     * Find all classes in a package that are records
     *
     * @param packageName The package path
     * @param loader      The class loader
     * @return List of all of the classes in the package that are records
     */
    public static List<Class<?>> getClassesThatAreRecords(String packageName, ClassLoader loader) {
        return getClassesInPackage(packageName, loader).stream().filter(c -> c.isRecord()).collect(Collectors.toList());
    }

}
