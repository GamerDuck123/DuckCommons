package com.gamerduck.commons.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * This class will allow you to register all of the AbstractDuckCommand classes
 *
 * @author GamerDuck123
 */
public class DuckCommandHandler {

    public DuckCommandHandler(String pluginname, List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (DuckCommand.class.isAssignableFrom(clazz)) {
                try {
                    ((DuckCommand) clazz.getConstructor().newInstance()).register(pluginname);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                         | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
