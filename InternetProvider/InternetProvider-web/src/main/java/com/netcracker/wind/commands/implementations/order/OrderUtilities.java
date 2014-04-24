/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.entities.ProviderLocation;
import java.util.List;

/**
 *
 * @author Anatolii
 */
public class OrderUtilities {

    private static double getDistance(double x1, double y1, double x2, double y2) {
        double a = x1 - x2;
        double b = y1 - y2;
        double distance = Math.sqrt(a * a + b * b);
        return distance;
    }

    public static ProviderLocation findNearestProviderLocation(
            List<ProviderLocation> providerLocations, double actualX, double actualY) {
        ProviderLocation nearestProviderLocation = null;
        double minDist = Double.MAX_VALUE;
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
