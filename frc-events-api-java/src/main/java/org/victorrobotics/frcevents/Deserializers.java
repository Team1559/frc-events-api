package org.victorrobotics.frcevents;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public final class Deserializers {
  private Deserializers() {}

  static SimpleModule module() {
    SimpleModule module = new SimpleModule();
    module.addDeserializer(URL.class, new UrlDeserializer());
    return module;
  }

  static class UrlDeserializer extends StdDeserializer<URL> {
    public UrlDeserializer() {
      this(null);
    }

    public UrlDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public URL deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = jp.getCodec()
                        .readTree(jp);
      String str = node.textValue();
      if (str == null || !str.startsWith("http")) {
        return null;
      }
      return new URL(str);
    }
  }

  public static class YesNoDeserializer extends StdDeserializer<Boolean> {
    public YesNoDeserializer() {
      this(null);
    }

    public YesNoDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = jp.getCodec()
                        .readTree(jp);
      String str = node.textValue();
      if ("Yes".equals(str)) {
        return Boolean.TRUE;
      } else if ("No".equals(str)) {
        return Boolean.FALSE;
      } else {
        return null;
      }
    }
  }
}
