package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AwardListing(@JsonProperty("Awards") List<Award> awards) {
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

  public static Endpoint<AwardListing> forEvent(int year, String eventCode) {
    return Endpoint.forSingle("/" + year + "/awards/event/" + eventCode, AwardListing.class);
  }

  public static Endpoint<AwardListing> forTeam(int year, int teamNumber) {
    return Endpoint.forSingle("/" + year + "/awards/team/" + teamNumber, AwardListing.class);
  }

  public static Endpoint<AwardListing> forEventTeam(int year, String eventCode, int teamNumber) {
    return Endpoint.forSingle("/" + year + "/awards/eventteam/" + eventCode + "/" + teamNumber,
                              AwardListing.class);
  }
}
