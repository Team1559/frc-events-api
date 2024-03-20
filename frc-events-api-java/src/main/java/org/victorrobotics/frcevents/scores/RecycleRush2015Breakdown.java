package org.victorrobotics.frcevents.scores;

import org.victorrobotics.frcevents.TournamentLevel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record RecycleRush2015Breakdown(@JsonProperty List<Alliance> alliances,
                                       @JsonProperty TournamentLevel matchLevel,
                                       @JsonProperty int matchNumber,
                                       @JsonProperty Coopertition coopertition,
                                       @JsonProperty int coopertitionPoints)
    implements ScoreBreakdown<RecycleRush2015Breakdown.Alliance> {
  public record Alliance(@JsonProperty AllianceColor alliance,
                         @JsonProperty boolean robotSet,
                         @JsonProperty boolean containerSet,
                         @JsonProperty boolean toteSet,
                         @JsonProperty boolean toteStack,
                         @JsonProperty int toteCountFar,
                         @JsonProperty int toteCountNear,
                         @JsonProperty int containerCountLevel1,
                         @JsonProperty int containerCountLevel2,
                         @JsonProperty int containerCountLevel3,
                         @JsonProperty int containerCountLevel4,
                         @JsonProperty int containerCountLevel5,
                         @JsonProperty int containerCountLevel6,
                         @JsonProperty int litterCountLandfill,
                         @JsonProperty int litterCountContainer,
                         @JsonProperty int litterCountUnprocessed,
                         @JsonProperty int foulCount,
                         @JsonProperty int autoPoints,
                         @JsonProperty int totePoints,
                         @JsonProperty int containerPoints,
                         @JsonProperty int litterPoints,
                         @JsonProperty int teleopPoints,
                         @JsonProperty int adjustPoints,
                         @JsonProperty int foulPoints,
                         @JsonProperty int totalPoints)
      implements ScoreBreakdown.Alliance {}

  public enum Coopertition {
    NONE("None"),
    STACK("Stack"),
    SET("Set"),
    UNKNOWN("Unknown");

    @JsonValue
    private final String value;

    Coopertition(String value) {
      this.value = value;
    }
  }
}
