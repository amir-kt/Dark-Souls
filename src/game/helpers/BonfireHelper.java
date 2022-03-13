package game.helpers;

import engine.GameMap;
import engine.Location;
import game.ground.Bonfire;

import java.util.ArrayList;

public class BonfireHelper {
    public static ArrayList<Location> findBonfires(GameMap map) {
        ArrayList<Location> bonfires = new ArrayList<>();
        for (int y : map.getYRange()) {
            for (int x : map.getXRange()) {
                if (map.at(x, y).getGround() instanceof Bonfire)
                    bonfires.add(map.at(x,y));
            }
        }
        return bonfires;
    }

    public static Location findBonfireLocation(GameMap map, Bonfire bonfire) {
        Location location;
        for (int y : map.getYRange()) {
            for (int x : map.getXRange()) {
                if (map.at(x, y).getGround() == bonfire)
                    return map.at(x,y);
            }
        }
        return null;
    }
}
