package org.victorrobotics.frcevents;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MatchResults(@JsonProperty("Matches") List<MatchResult> matches) {
  public record MatchResult(@JsonProperty String description,
                            @JsonProperty int matchNumber,
                            @JsonProperty TournamentLevel tournamentLevel,
                            @JsonProperty LocalDateTime actualStartTime,
                            @JsonProperty LocalDateTime postResultTime,
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
                            @JsonProperty URL matchVideoLink) {}

  public static Endpoint<MatchResults> forAll(int year, String eventCode) {
    return Endpoint.of("/" + year + "/matches/" + eventCode, MatchResults.class);
  }

  public static Endpoint<MatchResults> forLevel(int year, String eventCode, TournamentLevel level) {
    return forQuery(year, eventCode, new Query().withLevel(level));
  }

  public static Endpoint<MatchResults> forTeam(int year, String eventCode, int teamNumber) {
    return forQuery(year, eventCode, new Query().withTeam(teamNumber));
  }

  public static Endpoint<MatchResults> forQuery(int year, String eventCode, Query query) {
    return Endpoint.of("/" + year + "/matches/" + eventCode + query.build(), MatchResults.class);
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
