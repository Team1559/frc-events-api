package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventRankingsListing(@JsonProperty("Rankings") List<EventRanking> rankings) {
  public record EventRanking(@JsonProperty int rank,
                             @JsonProperty int teamNumber,
                             @JsonProperty int sortOrder1,
                             @JsonProperty int sortOrder2,
                             @JsonProperty int sortOrder3,
                             @JsonProperty int sortOrder4,
                             @JsonProperty int sortOrder5,
                             @JsonProperty int sortOrder6,
                             @JsonProperty int wins,
                             @JsonProperty int losses,
                             @JsonProperty int ties,
                             @JsonProperty double qualAverage,
                             @JsonProperty int dq,
                             @JsonProperty int matchesPlayed) {}

  private static String basePath(int year, String eventCode) {
    return "/" + year + "/rankings/" + eventCode;
  }

  public static Endpoint<EventRankingsListing> forAll(int year, String eventCode) {
    return Endpoint.forSingle(basePath(year, eventCode), EventRankingsListing.class);
  }

  public static Endpoint<EventRankingsListing> forTeam(int year, String eventCode, int teamNumber) {
    return Endpoint.forSingle(basePath(year, eventCode) + "?teamNumber=" + teamNumber,
                              EventRankingsListing.class);
  }

  public static Endpoint<EventRankingsListing> forTop(int year, String eventCode, int count) {
    return Endpoint.forSingle(basePath(year, eventCode) + "?top=" + count,
                              EventRankingsListing.class);
  }
}
