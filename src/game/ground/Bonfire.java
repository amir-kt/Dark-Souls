package game.ground;

import engine.Actions;
import engine.Actor;
import engine.Ground;
import engine.Location;
import game.actions.LightBonfireAction;
import game.actions.SoftResetAction;
import game.actions.TeleportAction;
import game.enums.Abilities;

import java.util.ArrayList;

public class Bonfire extends Ground {
    private String name;
    private boolean isLit = false;
    private ArrayList<Location> bonfires;

    public Bonfire() {
        super('B');
        this.addCapability(Abilities.BLINK_DESTINATION);
    }

    public Bonfire(String name) {
        super('B');
        this.name = name;
        this.addCapability(Abilities.BLINK_DESTINATION);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLit(boolean lit) {
        isLit = lit;
    }

    public void setBonfires(ArrayList<Location> bonfires) {
        this.bonfires = bonfires;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return super.canActorEnter(actor);
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (actor.hasCapability(Abilities.REST)) {
            if (isLit) {
                Actions actions = new Actions(new SoftResetAction(this));
                for (Location bonfire : bonfires) {
                    if (!bonfire.equals(location))
                    actions.add(new TeleportAction(bonfire));
                }
                return actions;
            }
            else
                return new Actions(new LightBonfireAction(this));
        }
        return new Actions();
    }

    @Override
    public String toString() {
        return name + "'s Bonfire";
    }
}
