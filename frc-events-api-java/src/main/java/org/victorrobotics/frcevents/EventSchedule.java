package org.victorrobotics.frcevents;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record EventSchedule(@JsonProperty("Schedule") List<Match> schedule) {
  public record Match(@JsonProperty String description,
                      @JsonProperty LocalDateTime startTime,
                      @JsonProperty int matchNumber,
                      @JsonProperty Field field,
                      @JsonProperty TournamentLevel tournamentLevel,
                      @JsonProperty List<MatchTeam> teams) {}

  public enum Field {
    PRIMARY("Primary");

    @JsonValue
    private final String value;

    Field(String value) {
      this.value = value;
    }
  }

  public static Endpoint<EventSchedule> forLevel(int year, String eventCode,
                                                 TournamentLevel level) {
    return forQuery(year, eventCode, new Query().withLevel(level));
  }

  public static Endpoint<EventSchedule> forTeam(int year, String eventCode, int teamNumber) {
    return forQuery(year, eventCode, new Query().withTeam(teamNumber));
  }

  public static Endpoint<EventSchedule> forQuery(int year, String eventCode, Query query) {
    return Endpoint.of("/" + year + "/schedule/" + eventCode + query.build(), EventSchedule.class);
  }

  public static class Query extends QueryBuilder {
    public Query withLevel(TournamentLevel level) {
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
