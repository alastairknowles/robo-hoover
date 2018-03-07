package com.yoti.rh.util;

public class CoordinateUtils {

    public static boolean isInBounds(Long[] lower, Long[] upper, Long[] point) {
        Long lowerX = lower[0];
        Long lowerY = lower[1];

        Long upperX = upper[0];
        Long upperY = upper[1];

        Long pointX = point[0];
        Long pointY = point[1];

        // We're using the bottom left-hand corner of each square to identify it's position in the grid
        // For that reason the current point needs to be >= the lower bound and < the upper bound
        return pointX >= lowerX && pointY >= lowerY && pointX < upperX && pointY < upperY;
    }

}
