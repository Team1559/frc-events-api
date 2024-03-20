package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.TournamentLevel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.util.StdConverter;

public sealed interface ScoreBreakdown<A extends ScoreBreakdown.Alliance>
    permits RapidReact2022Breakdown, ChargedUp2023Breakdown, Crescendo2024Breakdown {
  sealed interface Alliance
      permits RapidReact2022Breakdown.Alliance, ChargedUp2023Breakdown.Alliance,
      Crescendo2024Breakdown.Alliance {
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

  TournamentLevel matchLevel();

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

  class YesNoConverter extends StdConverter<String, Boolean> {
    @Override
    public Boolean convert(String str) {
      if ("Yes".equals(str)) {
        return Boolean.TRUE;
      } else if ("No".equals(str)) {
        return Boolean.FALSE;
      } else {
        return null;
      }
    }
  }
}
