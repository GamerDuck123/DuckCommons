package com.gamerduck.commons.persistent;

import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;

public class EntityTypeDataType implements PersistentDataType<byte[], EntityType> {


    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<EntityType> getComplexType() {
        return EntityType.class;
    }

    @Override
    public byte[] toPrimitive(EntityType type, PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(type);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EntityType fromPrimitive(byte[] bytes, PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (EntityType) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
