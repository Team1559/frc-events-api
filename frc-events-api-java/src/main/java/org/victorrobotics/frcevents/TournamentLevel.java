package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TournamentLevel {
  PRACTICE("Practice"),
  QUALIFICATION("Qualification"),
  PLAYOFF("Playoff"),

  @JsonEnumDefaultValue
  NONE("None");

  @JsonValue
  final String value;

  TournamentLevel(String value) {
    this.value = value;
  }
}
