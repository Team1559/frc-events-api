package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public final class DistrictRankingCalculations {
  private DistrictRankingCalculations() {}

  public enum AllianceRole {
    CAPTAIN("Captain"),
    ROUND_1("Round1"),
    ROUND_2("Round2"),
    ROUND_3("Round3"),
    BACKUP("Backup"),

    @JsonEnumDefaultValue
    NONE("None");

    @JsonValue
    private final String value;

    AllianceRole(String value) {
      this.value = value;
    }
  }

  public static Endpoint<Integer> qualsPerformancePoints(int year, EventType type,
                                                         int qualificationRank, int teamCount) {
    return Endpoint.forSingle("/" + year + "/rankings/district/qualPerformanceCalculation?type="
        + type.value + "&qualificationRank=" + qualificationRank + "&teamsAtEvent=" + teamCount,
                              Integer.class);
  }

  public static Endpoint<Integer> allianceSelectionPoints(int year, EventType type,
                                                          AllianceCount allianceCount,
                                                          int allianceNumber, AllianceRole role) {
    return Endpoint.forSingle("/" + year
        + "/rankings/district/allianceSelectionCalculation?tournamentType=" + type.value
        + "&sizeType=" + allianceCount.value + "&allianceNumber=" + allianceNumber
        + "&allianceRole=" + role.value, Integer.class);
  }

  public static Endpoint<Integer> playoffAdvancementPoints(int year, EventType type,
                                                           int quarterfinalWins, int semifinalWins,
                                                           int finalWins) {
    return Endpoint.forSingle("/" + year
        + "/rankings/district/playoffAdvancementCalculation?tournamentType=" + type.value
        + "&quarterFinalWins=" + quarterfinalWins + "&semiFinalWins=" + semifinalWins
        + "&finalWins=" + finalWins, Integer.class);
  }
}
