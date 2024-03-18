package org.victorrobotics.frcevents;

import org.victorrobotics.frcevents.EventListing.Event.TournamentType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AwardTypeListing(@JsonProperty List<AwardType> awards) {
  public record AwardType(@JsonProperty int awardId,
                          @JsonProperty TournamentType eventType,
                          @JsonProperty String description,
                          @JsonProperty("forPerson") boolean isForPerson) {}
}
