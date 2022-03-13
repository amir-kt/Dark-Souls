package game;

import engine.Location;

import static java.lang.Integer.max;

public class Utility {
    public static int distance(Location a, Location b) {
        return max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()));
    }
}

