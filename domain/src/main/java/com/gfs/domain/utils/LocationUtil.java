package com.gfs.domain.utils;

import redis.clients.jedis.GeoUnit;

import java.util.HashMap;
import java.util.Map;

public class LocationUtil {
    private static final Map<GeoUnit, Double> radiusMap = new HashMap<GeoUnit, Double>() {{
        put(GeoUnit.KM, 6371.0);
        put(GeoUnit.M, 6371000.0);
    }};

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public static double distanceOf(double lat1, double lon1, double lat2, double lon2, GeoUnit unit) {
        double radius = radiusMap.get(unit);

        double dLat = degreesToRadians(lat2 - lat1);
        double dLon = degreesToRadians(lon2 - lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c;
    }
}
