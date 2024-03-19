package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record ApiInfo(@JsonProperty int currentSeason,
                      @JsonProperty int maxSeason,
                      @JsonProperty String name,
                      @JsonProperty String apiVersion,
                      @JsonProperty Status status) {
  public enum Status {
    NORMAL("normal");

    @JsonValue
    private final String value;

    Status(String value) {
      this.value = value;
    }
  }

  public static Endpoint<ApiInfo> endpoint() {
    return Endpoint.of("/", ApiInfo.class);
  }
}
