package org.victorrobotics.frcevents;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeasonSummary(@JsonProperty int eventCount,
                            @JsonProperty String gameName,
                            @JsonProperty("kickoff") LocalDateTime kickoffTime,
                            @JsonProperty("rookieStart") int minRookieTeamNumber,
                            @JsonProperty int teamCount,
                            @JsonProperty("frcChampionships") List<Championship> championships) {
  public record Championship(@JsonProperty String name,
                             @JsonProperty LocalDateTime startDate,
                             @JsonProperty String location) {}

  public static Endpoint<SeasonSummary> endpoint(int year) {
    return Endpoint.forSingle("/" + year, SeasonSummary.class);
  }
}
