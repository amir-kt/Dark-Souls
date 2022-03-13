package game.helpers;

import engine.Actor;
import engine.GameMap;
import game.enemies.Skeleton;
import game.weapons.Broadsword;

public class SkeletonHelper {
    public static void addSkeletonToMap(GameMap map, int[] x_pos, int[] y_pos) {
        for (int i = 0; i < x_pos.length; i++){
            Actor skeleton = new Skeleton(x_pos[i], y_pos[i]);
            skeleton.addItemToInventory(new Broadsword());
            map.at(x_pos[i], y_pos[i]).addActor(skeleton);
        }
    }
}
