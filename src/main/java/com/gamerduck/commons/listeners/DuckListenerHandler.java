package com.gamerduck.commons.listeners;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

public class DuckListenerHandler {
	
	public DuckListenerHandler(JavaPlugin main, ArrayList<Class<?>> classes) {
		main.getLogger().info("Loading listeners..");
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
		main.getLogger().info("Finished loading listeners");
	}
	
}
