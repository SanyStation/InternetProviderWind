package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.entities.ProviderLocation;
import java.util.List;

/**
 * Class with static help methods.
 *
 * @author Anatolii
 */
public class OrderUtilities {

    private static final double MAX_DISTANCE = 0.15;

    private static double getDistance(double x1, double y1, double x2, double y2) {
        double a = x1 - x2;
        double b = y1 - y2;
        double distance = Math.sqrt(a * a + b * b);
        return distance;
    }

    /**
     * Method find nearest Provider Location to position.
     *
     * @param providerLocations list of all Provider Location
     * @param actualX x coordinate of needed position
     * @param actualY y coordinate of needed position
     * @return nearest Provider Location to position or null if all distances is
     * more MAX_DISTANCE
     */
    public static ProviderLocation findNearestProviderLocation(
            List<ProviderLocation> providerLocations, double actualX, double actualY) {
        ProviderLocation nearestProviderLocation = null;
        double minDist = MAX_DISTANCE;//Double.MAX_VALUE;
        for (ProviderLocation pl : providerLocations) {
            double dist = OrderUtilities.getDistance(actualX, actualY,
                    pl.getPosY(),
                    pl.getPosX());
            if (dist < minDist) {
                minDist = dist;
                nearestProviderLocation = pl;
            }
        }
        return nearestProviderLocation;
    }
}
