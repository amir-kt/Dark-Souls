package game.actions;

import engine.*;

public class DarkmoonRangedAttackAction extends Action {

    private Actor target;

    public DarkmoonRangedAttackAction(Actor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        if (here.x() == there.x() || here.y() == there.y()) {
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if(map.at(x, y).getGround().blocksThrownObjects())
                        return actor.toString() + " " + actor.getWeapon().verb() + " " + target + " but misses as there was something in the way";
                }
            }

            Action action = new PassiveWeaponAction(target);
            return action.execute(actor, map);
        }
        return actor.toString() + " can't attack" + actor.getWeapon().verb() + " " + target;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
