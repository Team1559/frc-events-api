package org.victorrobotics.frcevents;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/* NOTE: pages are 1-indexed */
public record Teams(@JsonProperty List<Team> teams,
                    @JsonProperty("teamCountTotal") int queryTeamCount,
                    @JsonProperty("teamCountPage") int pageTeamCount,
                    @JsonProperty("pageCurrent") int currentPageNumber,
                    @JsonProperty("pageTotal") int maxPageNumber) {
  public record Team(@JsonProperty String schoolName,
                     @JsonProperty URL website,
                     @JsonProperty String homeCMP,
                     @JsonProperty int teamNumber,
                     @JsonProperty String nameFull,
                     @JsonProperty String nameShort,
                     @JsonProperty String city,
                     @JsonProperty String stateProv,
                     @JsonProperty String country,
                     @JsonProperty int rookieYear,
                     @JsonProperty String robotName,
                     @JsonProperty String districtCode) {}

  public static Endpoint<Teams> forTeam(int year, int teamNumber) {
    return Endpoint.of("/" + year + "/teams?teamNumber=" + teamNumber, Teams.class);
  }

  public static Endpoint<Teams> forPage(int year, int pageNumber) {
    return forQuery(year, new Query().withPage(pageNumber));
  }

  public static Endpoint<Teams> forQuery(int year, Query query) {
    return Endpoint.of("/" + year + "/teams" + query.build(), Teams.class);
  }

  public static class Query extends QueryBuilder {
    public Query withEvent(String eventCode) {
      putParam("eventCode", eventCode);
      return this;
    }

    public Query withDistrict(String districtCode) {
      putParam("districtCode", districtCode);
      return this;
    }

    public Query withState(String stateName) {
      putParam("state", stateName);
      return this;
    }

    public Query withPage(int pageNumber) {
      putParam("page", pageNumber);
      return this;
    }
  }
}
