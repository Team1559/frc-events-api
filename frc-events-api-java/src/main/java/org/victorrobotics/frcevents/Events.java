package org.victorrobotics.frcevents;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record Events(@JsonProperty("Events") List<Event> events,
                     @JsonProperty int eventCount) {
  public record Event(@JsonProperty String name,
                      @JsonProperty String code,
                      @JsonProperty String divisionCode,
                      @JsonProperty String districtCode,
                      @JsonProperty EventType type,
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
                      /* Undocumented */
                      @JsonProperty int weekNumber,
                      @JsonProperty List<String> announcements,
                      @JsonProperty AllianceCount allianceCount) {}

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

  public static Endpoint<Events> forEvent(int year, String eventCode) {
    return Endpoint.forSingle("/" + year + "/events?eventCode=" + eventCode, Events.class);
  }

  public static Endpoint<Events> forAll(int year) {
    return Endpoint.forSingle("/" + year + "/events", Events.class);
  }

  public static Endpoint<Events> forTeam(int year, int teamNumber) {
    return forQuery(year, new Query().withTeam(teamNumber));
  }

  public static Endpoint<Events> forQuery(int year, Query query) {
    return Endpoint.forSingle("/" + year + "/events" + query.build(), Events.class);
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

    public Query withType(EventType type) {
      putParam("tournamentType", type);
      return this;
    }
  }
}
