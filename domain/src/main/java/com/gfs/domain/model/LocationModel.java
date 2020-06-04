package com.gfs.domain.model;

import com.gfs.domain.constant.LocationConstant;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.ArrayList;
import java.util.List;

public class LocationModel {

    private String type;
    private List<Double> coordinates;

    public LocationModel() {
        this.type = LocationConstant.LOCATION_POINT_TYPE;
    }

    public LocationModel(double longitude, double latitude) {
        this.type = LocationConstant.LOCATION_POINT_TYPE;
        this.coordinates = new ArrayList<Double>();
        this.coordinates.add(new Double(longitude));
        this.coordinates.add(new Double(latitude));
    }

    public LocationModel(GeoJsonPoint location) {
        this.type = location.getType();
        this.coordinates = location.getCoordinates();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}
