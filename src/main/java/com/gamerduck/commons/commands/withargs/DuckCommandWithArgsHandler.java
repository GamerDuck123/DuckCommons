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

    public DuckCommandWithArgsHandler(JavaPlugin main, String pluginname, List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            if (AbstractDuckCommandWithArgs.class.isAssignableFrom(clazz)) {
                Annotation[] annons = clazz.getAnnotations();
                for (Annotation annon : annons) {
                    if (annon instanceof DuckCommandWithArgs) {
                        DuckCommandWithArgs myannon = (DuckCommandWithArgs) annon;
                        try {
                            AbstractDuckCommandWithArgs cmdclass = (AbstractDuckCommandWithArgs) clazz.getConstructor().newInstance();
                            cmdclass.register(myannon.command(), myannon.arguments(), myannon.usageARGS(), myannon.description(), "", Arrays.asList(myannon.aliases()), pluginname);
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
