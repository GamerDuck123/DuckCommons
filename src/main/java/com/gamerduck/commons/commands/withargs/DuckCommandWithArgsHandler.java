package com.gamerduck.commons.commands.withargs;

import com.gamerduck.commons.commands.AbstractDuckCommand;
import com.gamerduck.commons.commands.DuckCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * This class will allow you to register all of the AbstractDuckCommand classes
 *
 * @author GamerDuck123
 */
public class DuckCommandWithArgsHandler {

    public DuckCommandWithArgsHandler(String pluginname, List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (AbstractDuckCommandWithArgs.class.isAssignableFrom(clazz)) {
                try {
                    AbstractDuckCommandWithArgs cmdclass = (AbstractDuckCommandWithArgs) clazz.getConstructor().newInstance();
                    cmdclass.register(pluginname);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                         | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
