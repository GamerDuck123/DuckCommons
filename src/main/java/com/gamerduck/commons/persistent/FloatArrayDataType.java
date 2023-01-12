package com.gamerduck.commons.persistent;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class FloatArrayDataType implements PersistentDataType<byte[], float[]> {

    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<float[]> getComplexType() {
        return float[].class;
    }

    @Override
    public byte[] toPrimitive(final float[] floats, final @NotNull PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeInt(floats.length);
            for (final float number : floats) {
                dos.writeFloat(number);
            }
            dos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float[] fromPrimitive(final byte[] bytes, @NotNull final PersistentDataAdapterContext itemTagAdapterContext) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             DataInputStream dis = new DataInputStream(bis)) {
            final float[] floats = new float[dis.readInt()];
            for (int i = 0; i < floats.length; i++) {
                floats[i] = dis.readFloat();
            }
            return floats;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
