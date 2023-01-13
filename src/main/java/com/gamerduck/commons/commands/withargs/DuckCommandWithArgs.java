package com.gamerduck.commons.commands.withargs;

import com.google.common.collect.Lists;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DuckCommandWithArgs {
    public String command() default "";

    public Class<? extends DuckArgument>[] arguments();

    public String[] aliases() default "";

    public String description() default "";

    public String usageARGS() default "";

    public String[] permissions() default "";
}
