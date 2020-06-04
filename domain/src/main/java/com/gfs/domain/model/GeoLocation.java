package com.gfs.domain.model;

import java.util.ArrayList;
import java.util.List;

public class GeoLocation {
    private static final String TYPE = "Point";
    private String type = TYPE;
    private List<Double> coordinates;

    public GeoLocation() {
    }

    public GeoLocation(Double x, Double y) {
        coordinates = new ArrayList<Double>();
        coordinates.add(x);
        coordinates.add(y);
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
