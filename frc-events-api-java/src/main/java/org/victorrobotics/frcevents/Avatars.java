package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Avatars(@JsonProperty List<TeamAvatar> teams,
                      @JsonProperty("teamCountTotal") int queryTeamCount,
                      @JsonProperty("teamCountPage") int pageTeamCount) {
  public record TeamAvatar(@JsonProperty int teamNumber,
                           @JsonProperty String encodedAvatar) {}

  public static Endpoint<Avatars> forTeam(int year, int teamNumber) {
    return Endpoint.of("/" + year + "/avatars?teamNumber=" + teamNumber, Avatars.class);
  }

  public static Endpoint<Avatars> forAll(int year, int pageNumber) {
    return forQuery(year, new Query().withPage(pageNumber));
  }

  public static Endpoint<Avatars> forQuery(int year, Query query) {
    return Endpoint.of("/" + year + "/avatars" + query.build(), Avatars.class);
  }

  public static class Query extends QueryBuilder {
    public Query withEvent(String eventCode) {
      putParam("eventCode", eventCode);
      return this;
    }

    public Query withPage(int pageNumber) {
      putParam("page", pageNumber);
      return this;
    }
  }
}
