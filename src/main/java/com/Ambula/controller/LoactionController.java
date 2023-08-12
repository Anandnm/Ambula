package com.Ambula.controller;

import com.Ambula.payload.LocationDto;
import com.Ambula.service.LocationService;
import com.Ambula.utility.HaversineDistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class LoactionController {
    @Autowired
    private LocationService locationService;

    //http://localhost:8080/api/user/1/locations
    @PostMapping("user/{userId}/locations")
    public ResponseEntity<LocationDto>createLocation(@PathVariable("userId") long userId, @RequestBody LocationDto locationDto){
        LocationDto location = locationService.createLocation(userId, locationDto);
        return new ResponseEntity<>(location, HttpStatus.CREATED);

    }
    @PutMapping("user/{userId}/locations/{id}")
    public ResponseEntity<LocationDto>updateLocation(@PathVariable(value = "userId") long userId, @PathVariable(value = "id") long locationsId, @RequestBody LocationDto locationDto){
        LocationDto dtos = locationService.updateLocation(userId, locationsId, locationDto);
        return new ResponseEntity<>(dtos,HttpStatus.OK);

    }
    @GetMapping("/get_users/{n}")
    public List<LocationDto> getNearestUsers(@PathVariable int n) {
        List<LocationDto> allLocations = locationService.getNearestLocations(n);
        return allLocations;
    }
}
