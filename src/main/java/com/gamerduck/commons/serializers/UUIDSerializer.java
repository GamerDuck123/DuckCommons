package com.gamerduck.commons.serializers;

import com.gamerduck.commons.items.DuckItem;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;

public class UUIDSerializer extends TypeAdapter<UUID> {
    @Override
    public void write(JsonWriter out, UUID id) throws IOException {
        if (id == null) {
            out.nullValue();
            return;
        }
        out.value(id.toString());
    }

    @Override
    public UUID read(JsonReader in) throws IOException {
        return UUID.fromString(in.nextString());
    }

}