package com.gamerduck.commons.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler {
	
	public CommandHandler(JavaPlugin main, String pluginname, ArrayList<Class<?>> classes) {
		main.getLogger().info("Loading commands..");
		for (Class<?> clazz : classes) {
	    	if (AbstractCommand.class.isAssignableFrom(clazz)) {
	    		Annotation[] annons = clazz.getAnnotations();
	    		for (Annotation annon : annons) {
	    			if (annon instanceof DuckCommand) {
	    				DuckCommand myannon = (DuckCommand) annon;
			    		try {
			    			AbstractCommand cmdclass = (AbstractCommand) clazz.getConstructor().newInstance();
							cmdclass.register(myannon.command(), myannon.usageARGS(), myannon.description(), "", Arrays.asList(myannon.aliases()), pluginname);
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
	    			}
	    		}
	    	}
		}
		main.getLogger().info("Finished loading commands");
	}
	
}
