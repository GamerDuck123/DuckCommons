package com.gamerduck.commons.persistent;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BooleanDataType implements PersistentDataType<Byte, Boolean> {
    @NotNull
    @Override
    public Class<Byte> getPrimitiveType() {
        return Byte.class;
    }

    @NotNull
    @Override
    public Class<Boolean> getComplexType() {
        return Boolean.class;
    }

    @Override
    public Byte toPrimitive(final Boolean bool, final @NotNull PersistentDataAdapterContext context) {
        return (bool ? (byte) 1 : (byte) 0);
    }

    @Override
    public Boolean fromPrimitive(final Byte by, final @NotNull PersistentDataAdapterContext context) {
        return by == 1 ? true : false;
    }
}
