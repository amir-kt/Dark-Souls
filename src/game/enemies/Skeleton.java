package game.enemies;

import game.AppConfig;
import game.behaviours.WanderBehaviour;

public class Skeleton extends Enemy{
    private int x_pos;
    private int y_pos;

    public Skeleton(int x_pos, int y_pos){
        super("Skeleton",'s', AppConfig.SKELETON_MAX_HIT_POINTS, new WanderBehaviour());
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.setSouls(AppConfig.SKELETON_SOULS);
    }
}
