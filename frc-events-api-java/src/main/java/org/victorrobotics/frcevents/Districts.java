package org.victorrobotics.frcevents;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Districts(@JsonProperty List<District> districts,
                        @JsonProperty int districtCount) {
  public record District(@JsonProperty String code,
                         @JsonProperty String name) {}

  public static Endpoint<Districts> forAll(int year) {
    return Endpoint.of("/" + year + "/districts", Districts.class);
  }
}
