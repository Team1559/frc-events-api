package org.victorrobotics.frcevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record MatchTeam(@JsonProperty int teamNumber,
                        @JsonProperty AllianceStation station,
                        @JsonProperty("dq") boolean isDisqualified) {
  public enum AllianceStation {
    RED_1("Red1", true, 1),
    RED_2("Red2", true, 2),
    RED_3("Red3", true, 3),
    BLUE_1("Blue1", false, 1),
    BLUE_2("Blue2", false, 2),
    BLUE_3("Blue3", false, 3);

    @JsonValue
    private final String value;

    private final boolean isRed;
    private final int     number;

    AllianceStation(String value, boolean isRed, int number) {
      this.value = value;
      this.isRed = isRed;
      this.number = number;
    }

    public boolean isRed() {
      return isRed;
    }

    public boolean isBlue() {
      return !isRed();
    }

    public int getNumber() {
      return number;
    }
  }
}
