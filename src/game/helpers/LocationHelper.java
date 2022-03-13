package game.helpers;

import engine.Location;

import static java.lang.Math.abs;

public class LocationHelper {
    public static String getDirection(Location from, Location to) {
        if (from.x() < to.x() && from.y() < to.y())
            return "South-East";
        if (from.x() < to.x() && from.y() == to.y())
            return "East";
        if (from.x() < to.x() && from.y() > to.y())
            return "North-East";
        if (from.x() == to.x() && from.y() > to.y())
            return "North";
        if (from.x() > to.x() && from.y() > to.y())
            return "North-West";
        if (from.x() > to.x() && from.y() == to.y())
            return "West";
        if (from.x() > to.x() && from.y() < to.y())
            return "South-West";
        return "South";
    }

    public static String getHotKeyForDirection(String direction) {
        return switch (direction) {
            case "West" -> "u";
            case "East" -> "v";
            case "North" -> "w";
            case "South" -> "x";
            case "South-East" -> "y";
            case "North-East" -> "z";
            case "South-West" -> "s";
            case "North-West" -> "t";
            default -> "";
        };
    }

    public static boolean isStraightPath(Location from, Location to) {
        return from.x() == to.x() || from.y() == to.y() || abs(from.y() - to.y()) == abs(from.x() - to.x());
    }
}