package game.ground;

import engine.Actor;
import engine.Ground;
import engine.Location;
import game.Player;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}

	/**
	 * @param actor the Actor to check
	 * @return true for player and false for all other actors
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return actor instanceof Player;
	}

	@Override
	public void tick(Location location) {
		Actor actor = location.getActor();
		if (actor != null) {
			actor.hurt(Integer.MAX_VALUE);
		}
	}
}
