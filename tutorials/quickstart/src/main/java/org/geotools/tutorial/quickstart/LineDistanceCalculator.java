package org.geotools.tutorial.quickstart;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.operation.distance.DistanceOp;

public class LineDistanceCalculator {

    public static void main(String[] args) {
        // Create LineStrings (you need to provide your own coordinates)
        Coordinate[] coordinates1 = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 0)
        };
        LineString lineString1 = new LineString(coordinates1, new PrecisionModel(), 0);

        Coordinate[] coordinates2 = new Coordinate[] {
                new Coordinate(0, 1),
                new Coordinate(1, 0),
                new Coordinate(2, 1)
        };
        LineString lineString2 = new LineString(coordinates2, new PrecisionModel(), 0);

        // Calculate the minimum distance between the two LineStrings
        double distance = calculateLineDistance(lineString1, lineString2);

        System.out.println("Minimum distance between the two lines: " + distance);
    }

    public static double calculateLineDistance(LineString lineString1, LineString lineString2) {
        DistanceOp distanceOp = new DistanceOp(lineString1, lineString2);
        return distanceOp.distance();
    }
}

