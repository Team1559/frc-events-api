package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DistrictRankings(@JsonProperty List<DistrictRanking> districtRanks,
                               @JsonProperty("rankingCountTotal") int queryRankingCount,
                               @JsonProperty("rankingCountPage") int pageRankingCount,
                               @JsonProperty("pageCurrent") int pageNumber,
                               @JsonProperty("pageTotal") int pageCount) {
  public record DistrictRanking(@JsonProperty String districtCode,
                                @JsonProperty int teamNumber,
                                @JsonProperty int rank,
                                @JsonProperty int totalPoints,
                                @JsonProperty String event1Code,
                                @JsonProperty double event1Points,
                                @JsonProperty String event2Code,
                                @JsonProperty double event2Points,
                                @JsonProperty String districtCmpCode,
                                @JsonProperty double districtCmpPoints,
                                @JsonProperty int teamAgePoints,
                                @JsonProperty int adjustmentPoints,
                                @JsonProperty boolean qualifiedDistrictCmp,
                                @JsonProperty boolean qualifiedFirstCmp) {}

  private static String basePath(int year, String districtCode) {
    return "/" + year + "/rankings/district?districtCode=" + districtCode;
  }

  public static Endpoint<DistrictRankings> forTeam(int year, int teamNumber) {
    return Endpoint.of("/" + year + "/rankings/district?teamNumber=" + teamNumber,
                       DistrictRankings.class);
  }

  public static Endpoint<DistrictRankings> forAll(int year, String districtCode) {
    return Endpoint.of(basePath(year, districtCode), DistrictRankings.class);
  }

  public static Endpoint<DistrictRankings> forTop(int year, String districtCode, int count) {
    return Endpoint.of(basePath(year, districtCode) + "&top=" + count, DistrictRankings.class);
  }

  public static Endpoint<DistrictRankings> forPage(int year, String districtCode, int pageNumber) {
    return Endpoint.of(basePath(year, districtCode) + "&page=" + pageNumber,
                       DistrictRankings.class);
  }
}
