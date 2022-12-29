package com.gamerduck.commons.serializers;

import com.gamerduck.commons.items.DuckItem;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DuckItemSerializer extends TypeAdapter<DuckItem> {
    @Override
    public void write(JsonWriter out, DuckItem item) throws IOException {
        if (item == null) {
            out.nullValue();
            return;
        }
        out.value(item.toBase64());
    }

    @Override
    public DuckItem read(JsonReader in) throws IOException {
        return DuckItem.fromBase64(in.nextString());
    }

}