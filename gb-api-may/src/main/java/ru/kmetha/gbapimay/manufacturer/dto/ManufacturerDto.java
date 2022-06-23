package ru.kmetha.gbapimay.manufacturer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDto {

    @JsonProperty(value = "id")
    private Long id;
    private String name;
}
