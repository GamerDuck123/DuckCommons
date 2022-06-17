package com.gamerduck.commons.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DuckCommand {
	public String command() default "";
	public String[] aliases() default "";
	public String description() default "";
	public String usageARGS() default "";
	public String[] permissions() default "";
}
