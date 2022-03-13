package game.ground;

import engine.Actor;
import engine.Ground;
import engine.Location;
import game.AppConfig;
import game.enemies.Undead;

/**
 * Cemetery which could randomly spawn an undead
 */
public class Cemetery extends Ground {

    public Cemetery() {
        super('c');
    }

    /**
     * 25% chance to spawn an Undead at the location
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (Math.floor(Math.random()*(100)+1) <= AppConfig.CEMETERY_SPAWN_UNDEAD_CHANCE) {
            location.addActor(new Undead());
        }
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }
}