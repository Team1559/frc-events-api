package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Alliances(@JsonProperty("Alliances") List<Alliance> alliances,
                        @JsonProperty int count) {
  public record Alliance(@JsonProperty int number,
                         @JsonProperty String name,
                         @JsonProperty int captain,
                         @JsonProperty int round1,
                         @JsonProperty int round2,
                         @JsonProperty Integer round3,
                         @JsonProperty Integer backup,
                         @JsonProperty Boolean backupReplaced) {}

  public static Endpoint<Alliances> forEvent(int year, String eventCode) {
    return Endpoint.of("/" + year + "/alliances/" + eventCode, Alliances.class);
  }
}
