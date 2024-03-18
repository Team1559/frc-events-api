package org.victorrobotics.frcevents;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record MatchResults(@JsonProperty("Matches") List<MatchResult> matches) {
  public record MatchResult(@JsonProperty("description") String name,
                            @JsonProperty("matchNumber") int number,
                            @JsonProperty("tournamentLevel") MatchLevel level,
                            @JsonProperty("actualStartTime") LocalDateTime startTime,
                            @JsonProperty("postResultTime") LocalDateTime resultTime,
                            @JsonProperty List<MatchTeam> teams,
                            @JsonProperty Integer scoreRedFinal,
                            @JsonProperty Integer scoreRedFoul,
                            @JsonProperty Integer scoreRedAuto,
                            @JsonProperty Integer scoreBlueFinal,
                            @JsonProperty Integer scoreBlueFoul,
                            @JsonProperty Integer scoreBlueAuto,
                            /* Undocumented */
                            @JsonProperty boolean isReplay,
                            @JsonProperty LocalDateTime autoStartTime,
                            @JsonProperty("matchVideoLink") URL videoUrl) {}

  public record MatchTeam(@JsonProperty int teamNumber,
                          @JsonProperty AllianceStation station,
                          @JsonProperty("dq") boolean disqualified) {}

  public enum MatchLevel {
    QUALIFICATION("Qualification"),
    PLAYOFF("Playoff"),

    @JsonEnumDefaultValue
    NONE("None");

    @JsonValue
    private final String value;

    MatchLevel(String value) {
      this.value = value;
    }
  }

  public enum AllianceStation {
    RED_1("Red1", true, 1),
    RED_2("Red2", true, 2),
    RED_3("Red3", true, 3),
    BLUE_1("Blue1", false, 1),
    BLUE_2("Blue2", false, 2),
    BLUE_3("Blue3", false, 3);

    @JsonValue
    private final String value;

    private final boolean isRed;
    private final int     number;

    AllianceStation(String value, boolean isRed, int number) {
      this.value = value;
      this.isRed = isRed;
      this.number = number;
    }

    public boolean isRed() {
      return isRed;
    }

    public boolean isBlue() {
      return !isRed();
    }

    public int getNumber() {
      return number;
    }
  }

  public static Endpoint<MatchResults> forAll(int year, String eventCode) {
    return Endpoint.forSingle("/" + year + "/matches/" + eventCode, MatchResults.class);
  }

  public static Endpoint<MatchResults> forLevel(int year, String eventCode, MatchLevel level) {
    return forQuery(year, eventCode, new Query().withLevel(level));
  }

  public static Endpoint<MatchResults> forTeam(int year, String eventCode, int teamNumber) {
    return forQuery(year, eventCode, new Query().withTeam(teamNumber));
  }

  public static Endpoint<MatchResults> forQuery(int year, String eventCode, Query query) {
    return Endpoint.forSingle("/" + year + "/matches/" + eventCode + query.build(),
                              MatchResults.class);
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
