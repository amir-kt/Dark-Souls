package game.weapons;

import engine.*;
import game.Utility;
import game.actions.*;
import game.enums.Status;

abstract public class GameWeaponItem extends WeaponItem {
    public int criticalHit = 0;
    public int hitRateIncrease = 0;
    protected int maxCharges = 0;
    private int charges = 0;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * In this game,
     * @param actor an actor that will interact with this item
     * @return null because
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }


    public Action getAttackAction(Actor actor, Actor target, GameMap map){
        if (actor.hasCapability(Status.STUNNED)){
            return new StunnedAction();
        }

        Weapon weapon = actor.getWeapon();

        if(!map.contains(target) && !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        int currentDistance = Utility.distance(here, there);

        if (currentDistance == 1) { //condition ensures actor will attack target only if in adjacent squares

            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();

                if (destination == there) {
                    return new PassiveWeaponAction(target, exit.getName());
                }
            }
        }

        return null;
    }

    public int getCriticalHit(){return criticalHit;}

    public void addCharge() {
        if (charged()) {
            charges = 0;
        } else {
            charges += 1;
        }
    }

    public String getCharge(){
        return charges + "/" + maxCharges;
    }

    public boolean charged(){
        return charges == maxCharges;
    }
}
