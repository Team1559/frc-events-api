package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.MatchLevel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

public sealed interface ScoreBreakdown<A extends ScoreBreakdown.Alliance>
    permits Crescendo2024Breakdown {
  sealed interface Alliance permits Crescendo2024Breakdown.Alliance {
    AllianceColor alliance();
  }

  enum AllianceColor {
    RED("Red"),
    BLUE("Blue");

    @JsonValue
    private final String value;

    AllianceColor(String value) {
      this.value = value;
    }
  }

  MatchLevel matchLevel();

  int matchNumber();

  List<A> alliances();

  default A blueAlliance() {
    return getAlliance(AllianceColor.BLUE);
  }

  default A redAlliance() {
    return getAlliance(AllianceColor.RED);
  }

  private A getAlliance(AllianceColor color) {
    List<A> alliances = alliances();
    for (A alliance : alliances) {
      if (alliance.alliance() == color) {
        return alliance;
      }
    }

    return null;
  }
}
