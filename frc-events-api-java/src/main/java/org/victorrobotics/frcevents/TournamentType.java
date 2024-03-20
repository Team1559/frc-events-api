package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonValue;

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
  final String value;

  TournamentType(String value) {
    this.value = value;
  }
}
