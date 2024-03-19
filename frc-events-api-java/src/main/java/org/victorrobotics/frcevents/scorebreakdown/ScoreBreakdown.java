package org.victorrobotics.frcevents.scorebreakdown;

import org.victorrobotics.frcevents.MatchLevel;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

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

  class YesNoDeserializer extends StdDeserializer<Boolean> {
    public YesNoDeserializer() {
      this(null);
    }

    public YesNoDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = jp.getCodec()
                        .readTree(jp);
      String str = node.textValue();
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
