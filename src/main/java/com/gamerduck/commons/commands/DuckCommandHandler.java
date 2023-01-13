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
            if (IDuckCommand.class.isAssignableFrom(clazz)) {
                try {
                    IDuckCommand cmdclass = (IDuckCommand) clazz.getConstructor().newInstance();
                    cmdclass.register(pluginname);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                         | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
