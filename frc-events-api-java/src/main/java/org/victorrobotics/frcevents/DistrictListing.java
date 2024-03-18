package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DistrictListing(@JsonProperty List<District> districts,
                              @JsonProperty int districtCount) {
  public record District(@JsonProperty String code,
                         @JsonProperty String name) {}

  public static Endpoint<DistrictListing> forAll(int year) {
    return Endpoint.forSingle("/" + year + "/districts", DistrictListing.class);
  }
}
