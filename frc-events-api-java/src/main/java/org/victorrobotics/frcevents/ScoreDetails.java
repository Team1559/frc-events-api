package org.victorrobotics.frcevents;

import org.victorrobotics.frcevents.scorebreakdown.ChargedUp2023Breakdown;
import org.victorrobotics.frcevents.scorebreakdown.Crescendo2024Breakdown;
import org.victorrobotics.frcevents.scorebreakdown.ScoreBreakdown;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

public record ScoreDetails<B extends ScoreBreakdown<?>>(@JsonProperty("MatchScores") List<B> scores) {
  public static final class ChargedUp2023 {
    private static final TypeReference<ScoreDetails<ChargedUp2023Breakdown>> CHARGED_UP_2023 =
        new TypeReference<ScoreDetails<ChargedUp2023Breakdown>>() {};

    private ChargedUp2023() {}

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>> forAll(String eventCode,
                                                                        MatchLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2023, CHARGED_UP_2023);
    }

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>>
        forMatch(String eventCode, MatchLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2023, CHARGED_UP_2023);
    }

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>>
        forQuery(String eventCode, MatchLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2023, CHARGED_UP_2023);
    }
  }

  public static final class Crescendo2024 {
    private static final TypeReference<ScoreDetails<Crescendo2024Breakdown>> CRESCENDO_2024 =
        new TypeReference<ScoreDetails<Crescendo2024Breakdown>>() {};

    private Crescendo2024() {}

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>> forAll(String eventCode,
                                                                        MatchLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2024, CRESCENDO_2024);
    }

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>>
        forMatch(String eventCode, MatchLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2024, CRESCENDO_2024);
    }

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>>
        forQuery(String eventCode, MatchLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2024, CRESCENDO_2024);
    }
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forAll(String eventCode, MatchLevel level, int year, TypeReference<ScoreDetails<B>> type) {
    return Endpoint.of("/" + year + "/scores/" + eventCode + "/" + level.value, type);
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forMatch(String eventCode, MatchLevel level, int matchNumber, int year,
               TypeReference<ScoreDetails<B>> type) {
    return Endpoint.of("/" + year + "/scores/" + eventCode + "/" + level.value + "?matchNumber="
        + matchNumber, type);
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forQuery(String eventCode, MatchLevel level, Query query, int year,
               TypeReference<ScoreDetails<B>> type) {
    return Endpoint.of("/" + year + "/scores/" + eventCode + "/" + level.value + query.build(),
                       type);
  }

  public static final class Query extends QueryBuilder {
    public Query withStart(int startMatchNumber) {
      putParam("start", startMatchNumber);
      return this;
    }

    public Query withEnd(int endMatchNumber) {
      putParam("end", endMatchNumber);
      return this;
    }
  }
}
