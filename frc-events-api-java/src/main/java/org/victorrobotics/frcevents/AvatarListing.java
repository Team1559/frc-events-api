package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AvatarListing(@JsonProperty List<TeamAvatar> teams,
                            @JsonProperty("teamCountTotal") int queryTeamCount,
                            @JsonProperty("teamCountPage") int pageTeamCount) {
  public record TeamAvatar(@JsonProperty int teamNumber,
                           @JsonProperty String encodedAvatar) {}

  public static Endpoint<AvatarListing> forTeam(int year, int teamNumber) {
    return Endpoint.forSingle("/" + year + "/avatars?teamNumber=" + teamNumber,
                              AvatarListing.class);
  }

  public static Endpoint<AvatarListing> forAll(int year, int pageNumber) {
    return forQuery(year, new Query().withPage(pageNumber));
  }

  public static Endpoint<AvatarListing> forQuery(int year, Query query) {
    return Endpoint.forSingle("/" + year + "/avatars" + query.build(), AvatarListing.class);
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
