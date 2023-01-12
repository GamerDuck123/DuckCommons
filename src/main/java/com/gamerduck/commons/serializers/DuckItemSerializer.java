package com.gamerduck.commons.serializers;

import com.gamerduck.commons.items.DuckItem;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DuckItemSerializer extends TypeAdapter<DuckItem> {
    @Override
    public void write(JsonWriter out, DuckItem item) throws IOException {
        if (item == null) {
            out.nullValue();
            return;
        }
        out.value(item.base64());
    }

    @Override
    public DuckItem read(JsonReader in) throws IOException {
        return DuckItem.base64(in.nextString());
    }

}