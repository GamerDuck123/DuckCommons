package com.gamerduck.commons.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This class acts a way for DuckCommandHandler to figure out what to register
 *
 * @author GamerDuck123
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DuckCommandIdentifier { }
