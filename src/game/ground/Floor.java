package game.ground;

import engine.Actor;
import engine.Ground;
import game.Player;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
		this.addCapability(Abilities.BLINK_DESTINATION);
	}

	public boolean canActorEnter(Actor actor) {
		return actor instanceof Player;
	}

}
