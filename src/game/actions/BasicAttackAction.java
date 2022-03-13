package game.actions;

import engine.*;
import game.Player;
import game.enemies.LordOfCinder;

public class BasicAttackAction extends Action {
    private Actor target;
    private String direction;
    private int damage = -1;

    public BasicAttackAction(Actor target) {
        this.target = target;
    }

    public BasicAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();

        if (damage == -1){
            damage = weapon.damage();
        }

        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);

        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);

            if (actor instanceof Player) {
                // transfer souls from the target actor to the attacking actor
                if (target.asSoul() != null && actor.asSoul() != null) {
                    (target.asSoul()).transferSouls(actor.asSoul());
                }
            }

            if (!(target instanceof Player)) {
                // remove actor if it is not the player
                map.removeActor(target);
            }

            if (target instanceof LordOfCinder) {
                result += System.lineSeparator() + "LORD OF CINDER HAS FALLEN";
            } else {
                result += System.lineSeparator() + target + " is killed.";
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " attacks " + target + " at " + direction;
    }
}
