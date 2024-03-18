package org.victorrobotics.frcevents;

import org.victorrobotics.frcevents.EventListing.Event.TournamentType;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record EventListing(@JsonProperty("Events") List<Event> events,
                           @JsonProperty int eventCount) {
  public record Event(@JsonProperty String name,
                      @JsonProperty String code,
                      @JsonProperty String divisionCode,
                      @JsonProperty String districtCode,
                      @JsonProperty TournamentType type,
                      @JsonProperty("venue") String locationName,
                      @JsonProperty String address,
                      @JsonProperty String city,
                      @JsonProperty("stateprov") String province,
                      @JsonProperty String country,
                      @JsonProperty LocalDateTime dateStart,
                      @JsonProperty LocalDateTime dateEnd,
                      @JsonProperty String timezone,
                      @JsonProperty URL website,
                      @JsonProperty List<URL> webcasts,
                      /* Not officially posted in the API */
                      @JsonProperty int weekNumber,
                      @JsonProperty List<String> announcements,
                      @JsonProperty AllianceCount allianceCount) {
    public enum TournamentType {
      REGIONAL("Regional"),

      OFF_SEASON("OffSeason"),
      OFF_SEASON_SYNC("OffSeasonWithAzureSync"),

      REMOTE("Remote"),

      DISTRICT_EVENT("DistrictEvent"),
      DISTRICT_CHAMPIONSHIP("DistrictChampionship"),
      DISTRICT_CHAMPIONSHIP_WITH_LEVELS("DistrictChampionshipWithLevels"),
      DISTRICT_CHAMPIONSHIP_DIVISION("DistrictChampionshipDivision"),

      CHAMPIONSHIP_DIVISION("ChampionshipDivision");

      @JsonValue
      private final String value;

      TournamentType(String value) {
        this.value = value;
      }
    }

    public enum AllianceCount {
      EIGHT("EightAlliance", 8),
      FOUR("FourAlliance", 4),
      TWO("TwoAlliance", 2);

      @JsonValue
      private final String value;

      private final int count;

      AllianceCount(String value, int count) {
        this.value = value;
        this.count = count;
      }

      public int toInt() {
        return count;
      }
    }
  }

  public static Endpoint<EventListing> forEvent(int year, String eventCode) {
    return Endpoint.forSingle("/" + year + "/events?eventCode=" + eventCode, EventListing.class);
  }

  public static Endpoint<EventListing> forAll(int year) {
    return Endpoint.forSingle("/" + year + "/events", EventListing.class);
  }

  public static Endpoint<EventListing> forTeam(int year, int teamNumber) {
    return forQuery(year, new Query().withTeam(teamNumber));
  }

  public static Endpoint<EventListing> forQuery(int year, Query query) {
    return Endpoint.forSingle("/" + year + "/events" + query.build(), EventListing.class);
  }

  public static class Query extends QueryBuilder {
    /* NOTE: this query appears to be broken */
    public Query withTeam(int teamNumber) {
      putParam("teamNumber", teamNumber);
      return this;
    }

    public Query withDistrict(String districtCode) {
      putParam("districtCode", districtCode);
      return this;
    }

    public Query excludeDistricts(boolean exclude) {
      putParam("excludeDistrict", exclude);
      return this;
    }

    public Query withWeek(int weekNumber) {
      putParam("weekNumber", weekNumber);
      return this;
    }

    public Query withType(TournamentType type) {
      putParam("tournamentType", type);
      return this;
    }
  }
}
