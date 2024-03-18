package org.victorrobotics.frcevents;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record EventSchedule(@JsonProperty("Schedule") List<Match> matches) {
  public record Match(@JsonProperty("description") String name,
                      @JsonProperty LocalDateTime startTime,
                      @JsonProperty("matchNumber") int number,
                      @JsonProperty Field field,
                      @JsonProperty("tournamentLevel") MatchLevel level,
                      @JsonProperty List<MatchTeam> teams) {}

  public enum Field {
    PRIMARY("Primary");

    @JsonValue
    private final String value;

    Field(String value) {
      this.value = value;
    }
  }

  public static Endpoint<EventSchedule> forLevel(int year, String eventCode, MatchLevel level) {
    return forQuery(year, eventCode, new Query().withLevel(level));
  }

  public static Endpoint<EventSchedule> forTeam(int year, String eventCode, int teamNumber) {
    return forQuery(year, eventCode, new Query().withTeam(teamNumber));
  }

  public static Endpoint<EventSchedule> forQuery(int year, String eventCode, Query query) {
    return Endpoint.forSingle("/" + year + "/schedule/" + eventCode + query.build(),
                              EventSchedule.class);
  }

  public static class Query extends QueryBuilder {
    public Query withLevel(MatchLevel level) {
      putParam("tournamentLevel", level.value);
      return this;
    }

    public Query withTeam(int teamNumber) {
      putParam("teamNumber", teamNumber);
      return this;
    }

    public Query withStart(int matchNumberStart) {
      putParam("start", matchNumberStart);
      return this;
    }

    public Query withEnd(int matchNumberEnd) {
      putParam("end", matchNumberEnd);
      return this;
    }
  }
}
