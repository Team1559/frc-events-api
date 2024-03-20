package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AwardTypes(@JsonProperty List<AwardType> awards) {
  public record AwardType(@JsonProperty int awardId,
                          @JsonProperty TournamentType eventType,
                          @JsonProperty String description,
                          @JsonProperty("forPerson") boolean isForPerson) {}
}
