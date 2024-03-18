package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchLevel {
  PRACTICE("Practice"),
  QUALIFICATION("Qualification"),
  PLAYOFF("Playoff"),

  @JsonEnumDefaultValue
  NONE("None");

  @JsonValue
  final String value;

  MatchLevel(String value) {
    this.value = value;
  }
}
