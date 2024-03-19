package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.MatchLevel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record Crescendo2024Breakdown(/* Required properties */
                                     @JsonProperty List<Alliance> alliances,
                                     @JsonProperty MatchLevel matchLevel,
                                     @JsonProperty int matchNumber,
                                     /* Year-specific properties */
                                     @JsonProperty boolean coopertitionBonusAchieved,
                                     @JsonProperty int melodyBonusThresholdCoop,
                                     @JsonProperty int melodyBonusThresholdNonCoop,
                                     @JsonProperty int melodyBonusThreshold,
                                     @JsonProperty int ensembleBonusStagePointsThreshold,
                                     @JsonProperty int ensembleBonusOnStageRobotsThreshold)
    implements ScoreBreakdown<Crescendo2024Breakdown.Alliance> {
  public record Alliance(@JsonProperty AllianceColor alliance,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean autoLineRobot1,
                         @JsonProperty EndGameState endGameRobot1,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean autoLineRobot2,
                         @JsonProperty EndGameState endGameRobot2,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean autoLineRobot3,
                         @JsonProperty EndGameState endGameRobot3,
                         @JsonProperty int autoAmpNoteCount,
                         @JsonProperty int autoSpeakerNoteCount,
                         @JsonProperty int teleopAmpNoteCount,
                         @JsonProperty int teleopSpeakerNoteCount,
                         @JsonProperty int teleopSpeakerNoteAmplifiedCount,
                         @JsonProperty boolean micCenterStage,
                         @JsonProperty boolean micStageLeft,
                         @JsonProperty boolean micStageRight,
                         @JsonProperty boolean trapCenterStage,
                         @JsonProperty boolean trapStageLeft,
                         @JsonProperty boolean trapStageRight,
                         @JsonProperty int autoPoints,
                         @JsonProperty int autoAmpNotePoints,
                         @JsonProperty int autoSpeakerNotePoints,
                         @JsonProperty int autoTotalNotePoints,
                         @JsonProperty int autoLeavePoints,
                         @JsonProperty int teleopPoints,
                         @JsonProperty int teleopAmpNotePoints,
                         @JsonProperty int teleopSpeakerNotePoints,
                         @JsonProperty int teleopSpeakerNoteAmplifiedPoints,
                         @JsonProperty int teleopTotalNotePoints,
                         @JsonProperty int endGameParkPoints,
                         @JsonProperty int endGameOnStagePoints,
                         @JsonProperty int endGameSpotLightBonusPoints,
                         @JsonProperty int endGameHarmonyPoints,
                         @JsonProperty int endGameNoteInTrapPoints,
                         @JsonProperty int endGameTotalStagePoints,
                         @JsonProperty boolean melodyBonusAchieved,
                         @JsonProperty boolean ensembleBonusAchieved,
                         @JsonProperty boolean coopertitionCriteriaMet,
                         @JsonProperty boolean coopNotePlayed,
                         @JsonProperty int foulCount,
                         @JsonProperty int techFoulCount,
                         @JsonProperty boolean g206Penalty,
                         @JsonProperty boolean g408Penalty,
                         @JsonProperty boolean g424Penalty,
                         @JsonProperty int adjustPoints,
                         @JsonProperty int foulPoints,
                         @JsonProperty int rp,
                         @JsonProperty int totalPoints)
      implements ScoreBreakdown.Alliance {}

  public enum EndGameState {
    STAGE_LEFT("StageLeft"),
    STAGE_RIGHT("StageRight"),
    CENTER_STAGE("CenterStage"),
    PARKED("Parked"),
    NONE("None");

    @JsonValue
    private final String value;

    EndGameState(String value) {
      this.value = value;
    }
  }
}
