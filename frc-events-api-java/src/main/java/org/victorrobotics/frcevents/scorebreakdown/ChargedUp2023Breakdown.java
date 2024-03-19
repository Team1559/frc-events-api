package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.MatchLevel;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public record ChargedUp2023Breakdown(@JsonProperty List<Alliance> alliances,
                                     @JsonProperty MatchLevel matchLevel,
                                     @JsonProperty int matchNumber)
    implements ScoreBreakdown<ChargedUp2023Breakdown.Alliance> {
  public record Alliance(@JsonProperty AllianceColor alliance,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean mobilityRobot1,
                         @JsonProperty EndGameState autoChargeStationRobot1,
                         @JsonProperty EndGameState endGameChargeStationRobot1,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean mobilityRobot2,
                         @JsonProperty EndGameState autoChargeStationRobot2,
                         @JsonProperty EndGameState endGameChargeStationRobot2,
                         @JsonDeserialize(using = YesNoDeserializer.class)
                         @JsonProperty boolean mobilityRobot3,
                         @JsonProperty EndGameState autoChargeStationRobot3,
                         @JsonProperty EndGameState endGameChargeStationRobot3,
                         @JsonProperty Community autoCommunity,
                         @JsonProperty int autoGamePieceCount,
                         @JsonProperty int autoMobilityPoints,
                         @JsonProperty int autoPoints,
                         @JsonProperty boolean autoDocked,
                         @JsonDeserialize(using = BridgeStateDeserializer.class)
                         @JsonProperty boolean autoBridgeState,
                         @JsonProperty int autoGamePiecePoints,
                         @JsonProperty int autoChargeStationPoints,
                         @JsonProperty Community teleopCommunity,
                         @JsonProperty int teleopGamePieceCount,
                         @JsonProperty int teleopPoints,
                         @JsonProperty int teleopGamePiecePoints,
                         @JsonProperty int extraGamePieceCount,
                         @JsonProperty int endGameChargeStationPoints,
                         @JsonProperty int endGameParkPoints,
                         @JsonDeserialize(using = BridgeStateDeserializer.class)
                         @JsonProperty boolean endGameBridgeState,
                         @JsonProperty List<Link> links,
                         @JsonProperty int linkPoints,
                         @JsonProperty boolean activationBonusAchieved,
                         @JsonProperty boolean sustainabilityBonusAchieved,
                         @JsonProperty boolean coopertitionCriteriaMet,
                         @JsonProperty int coopGamePieceCount,
                         @JsonProperty int totalChargeStationPoints,
                         @JsonProperty int foulCount,
                         @JsonProperty int techFoulCount,
                         @JsonProperty boolean g405Penalty,
                         @JsonProperty boolean h111Penalty,
                         @JsonProperty int adjustPoints,
                         @JsonProperty int foulPoints,
                         @JsonProperty int rp,
                         @JsonProperty int totalPoints)
      implements ScoreBreakdown.Alliance {}

  public record Community(@JsonProperty("T") List<Piece> top,
                          @JsonProperty("M") List<Piece> middle,
                          @JsonProperty("B") List<Piece> bottom) {}

  public record Link(@JsonProperty Row row,
                     @JsonProperty List<Integer> nodes) {}

  public enum Piece {
    NONE("None"),
    CONE("Cone"),
    CUBE("Cube");

    @JsonValue
    private final String value;

    Piece(String value) {
      this.value = value;
    }
  }

  public enum Row {
    BOTTOM("Bottom"),
    MIDDLE("Mid"),
    TOP("Top");

    @JsonValue
    private final String value;

    Row(String value) {
      this.value = value;
    }
  }

  public enum EndGameState {
    NONE("None"),
    PARKED("Park"),
    DOCKED("Docked");

    @JsonValue
    private final String value;

    EndGameState(String value) {
      this.value = value;
    }
  }

  static class BridgeStateDeserializer extends StdDeserializer<Boolean> {
    public BridgeStateDeserializer() {
      this(null);
    }

    public BridgeStateDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = jp.getCodec()
                        .readTree(jp);
      String str = node.textValue();
      if ("Level".equals(str)) {
        return Boolean.TRUE;
      } else if ("NotLevel".equals(str)) {
        return Boolean.FALSE;
      } else {
        return null;
      }
    }
  }
}
