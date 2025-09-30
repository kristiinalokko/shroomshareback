package ee.valiit.shroomshareback.service;

import ee.valiit.shroomshareback.controller.location.dto.LocationMapInfo;
import ee.valiit.shroomshareback.controller.maplocation.dto.MapLocationsRequest;
import ee.valiit.shroomshareback.persistence.location.Location;
import ee.valiit.shroomshareback.persistence.location.LocationMapper;
import ee.valiit.shroomshareback.persistence.location.LocationRepository;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocation;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocationMapper;
import ee.valiit.shroomshareback.persistence.shroomLocation.ShroomLocationRepository;
import ee.valiit.shroomshareback.util.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapLocationService {

    private final ShroomLocationRepository shroomlocationRepository;
    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;
    private final ShroomLocationRepository shroomLocationRepository;
    private final ShroomLocationMapper shroomLocationMapper;

    public List<LocationMapInfo> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locationMapper.toLocationMapInfos(locations);
    }

    public List<LocationMapInfo> findFilteredShroomLocations(MapLocationsRequest request) {
        List<Location> locations = shroomlocationRepository.findFilteredShroomLocationsBy(request.getShroomId(), request.getMinRating(), request.getLastActive());


        List<Location> locationsGeoFiltered = new ArrayList<>();
        for (Location location : locations) {
            if (locationIsWithinRadius(location, request)) {
                locationsGeoFiltered.add(location);
            }
        }


        return locationMapper.toLocationMapInfos(locationsGeoFiltered);

    }

    private boolean locationIsWithinRadius(Location location, MapLocationsRequest request) {
        if (request.getLatitude() == null || request.getLongitude() == null ||
                request.getRadiusKm() == null || location.getLatitude() == null ||
                location.getLongitude() == null) {
            return true; // Include if coordinates are missing
        }

        double distance = DistanceCalculator.calculateDistanceInKm(
                location.getLatitude().doubleValue(),
                location.getLongitude().doubleValue(),
                request.getLatitude().doubleValue(),
                request.getLongitude().doubleValue()
        );

        return distance <= request.getRadiusKm();
    }


    public List<LocationMapInfo> findShroomLocations(Integer shroomId) {
        List<ShroomLocation> shroomLocations = shroomLocationRepository.findShroomLocations(shroomId);
        return shroomLocationMapper.toLocationMapInfos(shroomLocations);
    }
}
