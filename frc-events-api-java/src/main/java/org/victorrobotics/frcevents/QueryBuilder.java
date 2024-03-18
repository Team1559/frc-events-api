package org.victorrobotics.frcevents;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

class QueryBuilder {
  private final Map<String, Object> params = new LinkedHashMap<>();

  protected QueryBuilder() {}

  protected <T> void putParam(String key, T param) {
    params.put(key, Objects.requireNonNull(param));
  }

  public String build() {
    if (params.isEmpty()) return "";

    StringBuilder query = new StringBuilder();
    char separator = '?';

    for (Map.Entry<String, ?> param : params.entrySet()) {
      query.append(separator)
           .append(param.getKey())
           .append('=')
           .append(param.getValue());
      separator = '&';
    }

    return query.toString();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getClass().getSimpleName())
           .append(" [params=")
           .append(params)
           .append("]");
    return builder.toString();
  }
}
