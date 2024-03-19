package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.MatchLevel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record RapidReact2022Breakdown(@JsonProperty List<Alliance> alliances,
                                      @JsonProperty MatchLevel matchLevel,
                                      @JsonProperty int matchNumber)
    implements ScoreBreakdown<RapidReact2022Breakdown.Alliance> {
  public record Alliance(@JsonProperty AllianceColor alliance,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean taxiRobot1,
                         @JsonProperty EndgameState endgameRobot1,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean taxiRobot2,
                         @JsonProperty EndgameState endgameRobot2,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean taxiRobot3,
                         @JsonProperty EndgameState endgameRobot3,
                         @JsonProperty int autoCargoLowerNear,
                         @JsonProperty int autoCargoLowerFar,
                         @JsonProperty int autoCargoLowerBlue,
                         @JsonProperty int autoCargoLowerRed,
                         @JsonProperty int autoCargoUpperNear,
                         @JsonProperty int autoCargoUpperFar,
                         @JsonProperty int autoCargoUpperBlue,
                         @JsonProperty int autoCargoUpperRed,
                         @JsonProperty int autoCargoTotal,
                         @JsonProperty int teleopCargoLowerNear,
                         @JsonProperty int teleopCargoLowerFar,
                         @JsonProperty int teleopCargoLowerBlue,
                         @JsonProperty int teleopCargoLowerRed,
                         @JsonProperty int teleopCargoUpperNear,
                         @JsonProperty int teleopCargoUpperFar,
                         @JsonProperty int teleopCargoUpperBlue,
                         @JsonProperty int teleopCargoUpperRed,
                         @JsonProperty int teleopCargoTotal,
                         @JsonProperty int matchCargoTotal,
                         @JsonProperty int autoTaxiPoints,
                         @JsonProperty int autoPoints,
                         @JsonProperty int autoCargoPoints,
                         @JsonProperty boolean quintetAchieved,
                         @JsonProperty int teleopPoints,
                         @JsonProperty int teleopCargoPoints,
                         @JsonProperty int endgamePoints,
                         @JsonProperty boolean cargoBonusRankingPoint,
                         @JsonProperty boolean hangarBonusRankingPoint,
                         @JsonProperty int foulCount,
                         @JsonProperty int techFoulCount,
                         @JsonProperty int adjustPoints,
                         @JsonProperty int foulPoints,
                         @JsonProperty int rp,
                         @JsonProperty int totalPoints)
      implements ScoreBreakdown.Alliance {}

  public enum EndgameState {
    NONE("None"),
    LOW("Low"),
    MID("Mid"),
    HIGH("High"),
    TRAVERSAL("Traversal");

    @JsonValue
    private final String value;

    EndgameState(String value) {
      this.value = value;
    }
  }
}
