package com.gamerduck.commons.persistent;

import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public interface DataTypes {

    PersistentDataType<byte[], EntityType> ENTITY_TYPE = new EntityTypeDataType();
    PersistentDataType<byte[], double[]> DOUBLE_ARRAY = new DoubleArrayDataType();
    PersistentDataType<byte[], float[]> FLOAT_ARRAY = new FloatArrayDataType();
    PersistentDataType<byte[], boolean[]> BOOLEAN_ARRAY = new BooleanArrayDataType();
    PersistentDataType<Byte, Boolean> BOOLEAN = new BooleanDataType();
    PersistentDataType<int[], char[]> CHAR_ARRAY = new CharArrayDataType();
    PersistentDataType<byte[], short[]> SHORT_ARRAY = new ShortArrayDataType();
    PersistentDataType<byte[], String[]> STRING_ARRAY = new StringArrayDataType(StandardCharsets.UTF_8);
    PersistentDataType<byte[], java.util.UUID> UUID = new UUIDDataType();
}
