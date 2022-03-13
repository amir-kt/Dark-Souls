package game.actions;

import engine.*;
import game.weapons.GameWeaponItem;
import game.enums.Status;

import java.util.Random;

public class PassiveWeaponAction extends Action {

    private Actor target;
    private String direction;

    public PassiveWeaponAction(Actor target) {
        this.target = target;
    }

    public PassiveWeaponAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();
        Random rand = new Random();


        //passive - increased chance to hit if actor is enraged
        int hitRate = weapon.chanceToHit();
        if (actor.hasCapability(Status.ENRAGED)){
            hitRate += ((GameWeaponItem) weapon).hitRateIncrease;
        }

        //chance for weapon to miss
        if (!(rand.nextInt(100) <= hitRate)) {
            return actor + " misses " + target + ".";
        }

        //passive - chance of double damage from critical strike
        int damage = weapon.damage();
        if (weapon instanceof GameWeaponItem && !(rand.nextInt(100) <= ((GameWeaponItem)weapon).getCriticalHit())){
            damage *= 2;
        }


        BasicAttackAction action;
        if (direction != null) {
            action = new BasicAttackAction(target, direction);
        }
        else{
            action = new BasicAttackAction(target);
        }

        action.setDamage(damage);
        return action.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " attacks " + target + " at " + direction;
    }
}
