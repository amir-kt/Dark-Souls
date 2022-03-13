package game.ground;

import engine.Actions;
import engine.Actor;
import engine.Ground;
import engine.Location;
import game.Player;
import game.actions.TeleportAction;
import game.enums.Abilities;

public class Door extends Ground {
    private Location destination;
    private String destName;

    public Door() {
        super('=');
        this.addCapability(Abilities.BLINK_DESTINATION);
    }

    public Door(Location dest, String name) {
        super('=');
        destination = dest;
        destName = name;
        this.addCapability(Abilities.BLINK_DESTINATION);
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    /**
     * @param actor the Actor to check
     * @return true for player and false for all other actors
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor instanceof Player;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return returns an instance of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.getActor() == actor) {
            return new Actions(new TeleportAction(destination));
        }
        return new Actions();
    }

    @Override
    public String toString() {
        return destName;
    }
}
