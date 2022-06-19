package com.gamerduck.commons.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
/**
 * This class will allow you to register all of the AbstractDuckCommand classes
 * 
 * @author GamerDuck123
 *
 */
public class DuckCommandHandler {
	
	public DuckCommandHandler(JavaPlugin main, String pluginname, List<Class<?>> classes) {
		for (Class<?> clazz : classes) {
	    	if (AbstractDuckCommand.class.isAssignableFrom(clazz)) {
	    		Annotation[] annons = clazz.getAnnotations();
	    		for (Annotation annon : annons) {
	    			if (annon instanceof DuckCommand) {
	    				DuckCommand myannon = (DuckCommand) annon;
			    		try {
			    			AbstractDuckCommand cmdclass = (AbstractDuckCommand) clazz.getConstructor().newInstance();
							cmdclass.register(myannon.command(), myannon.usageARGS(), myannon.description(), "", Arrays.asList(myannon.aliases()), pluginname);
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
