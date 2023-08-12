package com.Ambula.service;

import com.Ambula.payload.LocationDto;

import java.util.List;

public interface LocationService {
    LocationDto createLocation(long userId, LocationDto locationDto);

    LocationDto updateLocation(long userId, long locationsId, LocationDto locationDto);


    List<LocationDto> getNearestLocations(int n);
}
