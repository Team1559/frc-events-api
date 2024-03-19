package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Awards(@JsonProperty("Awards") List<Award> awards) {
  public record Award(@JsonProperty int awardId,
                      @JsonProperty int teamId,
                      @JsonProperty int eventId,
                      @JsonProperty int eventDivisionId,
                      @JsonProperty String eventCode,
                      @JsonProperty String name,
                      @JsonProperty int series,
                      @JsonProperty int teamNumber,
                      @JsonProperty String schoolName,
                      @JsonProperty String fullTeamName,
                      @JsonProperty String person) {}

  public static Endpoint<Awards> forEvent(int year, String eventCode) {
    return Endpoint.of("/" + year + "/awards/event/" + eventCode, Awards.class);
  }

  public static Endpoint<Awards> forTeam(int year, int teamNumber) {
    return Endpoint.of("/" + year + "/awards/team/" + teamNumber, Awards.class);
  }

  public static Endpoint<Awards> forEventTeam(int year, String eventCode, int teamNumber) {
    return Endpoint.of("/" + year + "/awards/eventteam/" + eventCode + "/" + teamNumber,
                       Awards.class);
  }
}
