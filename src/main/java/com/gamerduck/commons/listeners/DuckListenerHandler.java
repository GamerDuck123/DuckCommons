package com.gamerduck.commons.listeners;

import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * This class will allow you to register all of the AbstractDuckListener classes
 *
 * @author GamerDuck123
 */
public class DuckListenerHandler {

    public DuckListenerHandler(JavaPlugin main, List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (AbstractDuckListener.class.isAssignableFrom(clazz)) {
                Annotation[] annons = clazz.getAnnotations();
                for (Annotation annon : annons) {
                    if (annon instanceof DuckListener) {
                        try {
                            AbstractDuckListener listenerClass = (AbstractDuckListener) clazz.getConstructor().newInstance();
                            main.getServer().getPluginManager().registerEvents(listenerClass, main);
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                                 | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
