package com.Ambula.service.impl;

import com.Ambula.entity.Location;
import com.Ambula.entity.User;
import com.Ambula.exception.RessourceNotFoundException;
import com.Ambula.payload.LocationDto;
import com.Ambula.repository.LocationRepository;
import com.Ambula.repository.UserRepository;
import com.Ambula.service.LocationService;
import com.Ambula.utility.HaversineDistanceCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LocationDto createLocation(long userId, LocationDto locationDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RessourceNotFoundException("Post Not Found By Id" + userId));
        Location locations = mapToEntity(locationDto);
        locations.setUser(user);
        Location save = locationRepository.save(locations);
      return  mapToDto(save);

    }

    @Override
    public LocationDto updateLocation(long userId, long locationsId, LocationDto locationDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RessourceNotFoundException("Post Not Found By Id" + userId));
        Location location = locationRepository.findById(locationsId).orElseThrow(() -> new RessourceNotFoundException("Location Not Found" + locationsId));
        location.setName(locationDto.getName());
        location.setLongitude(locationDto.getLongitude());
        location.setLatitude(locationDto.getLatitude());
        location.setExcluded(locationDto.isExcluded());
        Location save = locationRepository.save(location);
        return  mapToDto(save);


    }

    @Override
    public List<LocationDto> getNearestLocations(int n) {
        List<Location> allLocations = locationRepository.findByExcludedFalse();

        allLocations.sort((loc1, loc2) -> Double.compare(
                HaversineDistanceCalculator.calculateDistance(0.0, 0.0, loc1.getLatitude(), loc1.getLongitude()),
                HaversineDistanceCalculator.calculateDistance(0.0, 0.0, loc2.getLatitude(), loc2.getLongitude())
        ));

        // Convert and return the nearest N locations or all locations if N is greater than the list size
        if (n >= allLocations.size()) {
            return allLocations.stream().map(this::convertToDto).collect(Collectors.toList());
        } else {
            return allLocations.subList(0, n).stream().map(this::convertToDto).collect(Collectors.toList());
        }
    }



    private LocationDto mapToDto(Location location){
       return modelMapper.map(location,LocationDto.class);
}
private Location mapToEntity(LocationDto locationDto){
       return modelMapper.map(locationDto,Location.class);
}
    private LocationDto convertToDto(Location location) {
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setExcluded(location.isExcluded());
        return dto;
    }
}
