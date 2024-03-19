package org.victorrobotics.frcevents;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class Endpoint<T> implements Supplier<Optional<T>> {
  private static final Map<String, WeakReference<Endpoint<?>>> ENDPOINTS = new HashMap<>();

  private static final String API_URL = "https://frc-api.firstinspires.org/v3.0";
  private static final String AUTH_HEADER;

  static {
    String username = System.getenv("FRC_EVENTS_API_USERNAME");
    String token = System.getenv("FRC_EVENTS_API_TOKEN");
    if (username == null || token == null) {
      throw new FrcAuthException();
    }
    String authStr = username + ":" + token;
    AUTH_HEADER = "Basic " + Base64.getEncoder()
                                   .encodeToString(authStr.getBytes(StandardCharsets.UTF_8));
  }

  public static class FrcAuthException extends RuntimeException {
    public FrcAuthException() {
      super("Missing FRC_EVENTS_API_USERNAME and/or FRC_EVENTS_API_TOKEN environment variables");
    }
  }

  private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

  static final ObjectMapper JSON_OBJECT_MAPPER =
      new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                        .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
                        .registerModule(new JavaTimeModule())
                        .registerModule(Deserializers.module());

  private final Request.Builder requestBuilder;
  private final ObjectReader    jsonReader;

  @SuppressWarnings("java:S3077")
  private volatile Optional<T> value;

  private Endpoint(String endpoint, ObjectReader reader) {
    jsonReader = reader;
    requestBuilder = new Request.Builder().get()
                                          .url(API_URL + endpoint)
                                          .header("Authorization", AUTH_HEADER)
                                          .cacheControl(CacheControl.FORCE_NETWORK);
    value = Optional.empty();
  }

  public URL getUrl() {
    return requestBuilder.build()
                         .url()
                         .url();
  }

  public Optional<T> get() {
    return value;
  }

  public Optional<T> refresh() throws IOException {
    Call call = HTTP_CLIENT.newCall(requestBuilder.build());
    try (Response response = call.execute()) {
      update(response);
    }

    return get();
  }

  private void update(Response response) throws IOException {
    int statusCode = response.code();
    if (statusCode != 200 && statusCode != 304) return;

    String etag = response.headers()
                          .get("Last-Modified");
    if (etag != null) {
      requestBuilder.header("If-Modified-Since", etag);
    }

    if (statusCode != 200) return;

    try (InputStream body = response.body()
                                    .byteStream()) {
      T result = jsonReader.readValue(body);
      if (value.isEmpty() || !value.get()
                                   .equals(result)) {
        value = Optional.of(result);
      }
    }
  }

  @SuppressWarnings("unchecked")
  static <T> Endpoint<T> of(String endpoint, Class<T> clazz) {
    WeakReference<Endpoint<?>> value = ENDPOINTS.get(endpoint);
    if (value == null || value.get() == null) {
      value = new WeakReference<>(new Endpoint<>(endpoint, JSON_OBJECT_MAPPER.readerFor(clazz)));
      ENDPOINTS.put(endpoint, value);
    }
    return (Endpoint<T>) value.get();
  }

  @SuppressWarnings("unchecked")
  static <T> Endpoint<T> of(String endpoint, TypeReference<T> type) {
    WeakReference<Endpoint<?>> value = ENDPOINTS.get(endpoint);
    if (value == null || value.get() == null) {
      value = new WeakReference<>(new Endpoint<>(endpoint, JSON_OBJECT_MAPPER.readerFor(type)));
      ENDPOINTS.put(endpoint, value);
    }
    return (Endpoint<T>) value.get();
  }
}
