package game.enemies;

import engine.*;
import game.actions.PassiveWeaponAction;
import game.actions.StunnedAction;
import game.behaviours.FollowAttackBehaviour;
import game.Player;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import static java.lang.Math.max;

public class Enemy extends Actor implements Soul, Resettable {

    private Actor target;
    private Behaviour behaviour;
    private int souls;
    private Behaviour defaultBehaviour;

    public Enemy(String name, char displayChar, int hitPoints, Behaviour behaviour) {
        super(name, displayChar, hitPoints);
        this.behaviour = behaviour;
        this.defaultBehaviour = behaviour;
        this.registerInstance();
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public void setTarget(Actor target) {
        this.target = target;
        behaviour = new FollowAttackBehaviour(target);
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new PassiveWeaponAction(this, direction));
        }
        return actions;
    }


    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.STUNNED)){
            actions.add(new StunnedAction());
        }

        if (hitPoints < maxHitPoints/2){
            addCapability(Status.ENRAGED);
        }

        Location here = map.locationOf(this);

        //checks all spaces around and if any of the actors are player type, they become the target
        if (target == null) {

            for (Exit exit : here.getExits()) {

                Location destination = exit.getDestination();
                Actor potentialTarget = map.getActorAt(destination);

                if (potentialTarget instanceof Player) {
                    this.setTarget(potentialTarget);

                }
            }
        }

        Action action = behaviour.getAction(this, map);

        //gives empty Action if none are possible (error handling)
        if (action != null) {
            return action;
        }
        return new DoNothingAction();
    }

    @Override
    public String toString() {
        return name + "(" + Math.max(hitPoints,0) + "/" + maxHitPoints + ")(" + getWeapon().toString() + ")";
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
        this.subtractSouls(souls);
    }

    @Override
    public boolean subtractSouls(int souls) {
        this.setSouls(max(getSouls()-souls, 0));
        return true;
    }

    @Override
    public void resetInstance() {
        behaviour = defaultBehaviour;
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
