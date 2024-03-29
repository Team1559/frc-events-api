package org.victorrobotics.frcevents;

import org.victorrobotics.frcevents.Events.Event;
import org.victorrobotics.frcevents.scores.ChargedUp2023Breakdown;
import org.victorrobotics.frcevents.scores.Crescendo2024Breakdown;
import org.victorrobotics.frcevents.scores.RapidReact2022Breakdown;
import org.victorrobotics.frcevents.scores.RecycleRush2015Breakdown;
import org.victorrobotics.frcevents.scores.ScoreBreakdown;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

public record ScoreDetails<B extends ScoreBreakdown<?>>(@JsonProperty("MatchScores") List<B> scores) {
  public static final class RecycleRush2015 {
    private static final TypeReference<ScoreDetails<RecycleRush2015Breakdown>> RECYCLE_RUSH_2015 =
        new TypeReference<ScoreDetails<RecycleRush2015Breakdown>>() {};

    private RecycleRush2015() {}

    public static Endpoint<ScoreDetails<RecycleRush2015Breakdown>> forAll(String eventCode,
                                                                          TournamentLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2015, RECYCLE_RUSH_2015);
    }

    public static Endpoint<ScoreDetails<RecycleRush2015Breakdown>>
        forMatch(String eventCode, TournamentLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2015, RECYCLE_RUSH_2015);
    }

    public static Endpoint<ScoreDetails<RecycleRush2015Breakdown>>
        forQuery(String eventCode, TournamentLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2015, RECYCLE_RUSH_2015);
    }
  }

  public static final class RapidReact2022 {
    private static final TypeReference<ScoreDetails<RapidReact2022Breakdown>> RAPID_REACT_2022 =
        new TypeReference<ScoreDetails<RapidReact2022Breakdown>>() {};

    private RapidReact2022() {}

    public static Endpoint<ScoreDetails<RapidReact2022Breakdown>> forAll(String eventCode,
                                                                         TournamentLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2022, RAPID_REACT_2022);
    }

    public static Endpoint<ScoreDetails<RapidReact2022Breakdown>>
        forMatch(String eventCode, TournamentLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2022, RAPID_REACT_2022);
    }

    public static Endpoint<ScoreDetails<RapidReact2022Breakdown>>
        forQuery(String eventCode, TournamentLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2022, RAPID_REACT_2022);
    }
  }

  public static final class ChargedUp2023 {
    private static final TypeReference<ScoreDetails<ChargedUp2023Breakdown>> CHARGED_UP_2023 =
        new TypeReference<ScoreDetails<ChargedUp2023Breakdown>>() {};

    private ChargedUp2023() {}

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>> forAll(String eventCode,
                                                                        TournamentLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2023, CHARGED_UP_2023);
    }

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>>
        forMatch(String eventCode, TournamentLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2023, CHARGED_UP_2023);
    }

    public static Endpoint<ScoreDetails<ChargedUp2023Breakdown>>
        forQuery(String eventCode, TournamentLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2023, CHARGED_UP_2023);
    }
  }

  public static final class Crescendo2024 {
    private static final TypeReference<ScoreDetails<Crescendo2024Breakdown>> CRESCENDO_2024 =
        new TypeReference<ScoreDetails<Crescendo2024Breakdown>>() {};

    private Crescendo2024() {}

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>> forAll(String eventCode,
                                                                        TournamentLevel level) {
      return ScoreDetails.forAll(eventCode, level, 2024, CRESCENDO_2024);
    }

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>>
        forMatch(String eventCode, TournamentLevel level, int matchNumber) {
      return ScoreDetails.forMatch(eventCode, level, matchNumber, 2024, CRESCENDO_2024);
    }

    public static Endpoint<ScoreDetails<Crescendo2024Breakdown>>
        forQuery(String eventCode, TournamentLevel level, Query query) {
      return ScoreDetails.forQuery(eventCode, level, query, 2024, CRESCENDO_2024);
    }
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forAll(String eventCode, TournamentLevel level, int year,
             TypeReference<ScoreDetails<B>> type) {
    return Endpoint.of("/" + year + "/scores/" + eventCode + "/" + level.value, type);
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forMatch(String eventCode, TournamentLevel level, int matchNumber, int year,
               TypeReference<ScoreDetails<B>> type) {
    return Endpoint.of("/" + year + "/scores/" + eventCode + "/" + level.value + "?matchNumber="
        + matchNumber, type);
  }

  private static <B extends ScoreBreakdown<?>>
      Endpoint<ScoreDetails<B>>
      forQuery(String eventCode, TournamentLevel level, Query query, int year,
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
