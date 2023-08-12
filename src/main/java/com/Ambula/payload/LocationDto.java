package com.Ambula.payload;

import lombok.Data;

@Data
public class LocationDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private boolean excluded;
}
