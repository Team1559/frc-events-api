package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AllianceCount {
  TWO("TwoAlliance", 2),
  FOUR("FourAlliance", 4),
  SIX("SixAlliance", 6),
  EIGHT("EightAlliance", 8),
  SIXTEEN("SixteenAlliance", 16);

  @JsonValue
  final String value;

  private final int count;

  AllianceCount(String value, int count) {
    this.value = value;
    this.count = count;
  }

  public int toInt() {
    return count;
  }
}
