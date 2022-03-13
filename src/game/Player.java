package game;

import engine.*;
import game.actions.PlayerDiedAction;
import game.actions.TeleportAction;
import game.enums.Abilities;
import game.enums.Status;
import game.helpers.LocationHelper;
import game.interfaces.Blink;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import static java.lang.Math.max;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable, Blink {

	private final Menu menu = new Menu();
	private int souls;
	private Location spawnLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location spawnLocation) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.DRINK);
		this.addCapability(Abilities.RECEIVE_SOULS);
		this.addCapability(Abilities.CAN_TELEPORT);
		this.souls = AppConfig.PLAYER_STARTING_SOULS;
		this.maxHitPoints = hitPoints;
		this.hitPoints = hitPoints;
		this.spawnLocation = spawnLocation;
		this.registerInstance();
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public void setSouls(int souls) {
		this.souls = souls;
	}

	public int getSouls() {
		return souls;
	}

	/**
	 *
	 * @param map game map
	 * @param actor player
	 * @return returns an Actions instance including zero to multiple Teleport actions based on the location of the actor
	 *
	 * this method goes through every location in the map and determines whether the actor can blink there or not
	 * and then returns all the places the actor can blink to
	 */
	@Override
	public Actions getBlinkActionsForActor(GameMap map, Actor actor) {
		Actions actions = new Actions();

		for (int y : map.getYRange()) {
			for (int x : map.getXRange()) {
				Location destination = map.at(x,y);
				if (destination.getGround().hasCapability(Abilities.BLINK_DESTINATION) &&
						Utility.distance(map.at(x,y), map.locationOf(actor)) == AppConfig.PLAYER_BLINK_DISTANCE &&
						destination.canActorEnter(actor) &&
						LocationHelper.isStraightPath(map.locationOf(actor), destination)) {
					String direction = LocationHelper.getDirection(map.locationOf(actor), destination);
					String hotkey = LocationHelper.getHotKeyForDirection(direction);
					actions.add(new TeleportAction(destination, direction, hotkey));
				}
			}
		}
		return actions;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		System.out.println(name + " (" + hitPoints + "/" + maxHitPoints + "), holding " + getWeapon().toString() + ", " + this.getSouls() + " souls");
		if (this.isConscious()) {

			actions.add(getBlinkActionsForActor(map, this));

			Item tokenOfSouls = null;

			for (Item item : this.getInventory()) {
				if (item instanceof TokenOfSouls) {
					item.asSoul().transferSouls(this.asSoul());
					tokenOfSouls = item;
					break;
				}
			}
			if (tokenOfSouls != null)
				this.removeItemFromInventory(tokenOfSouls);


			// Handle multi-turn Actions
			if (lastAction.getNextAction() != null)
				return lastAction.getNextAction();

			// return/print the console menu
			return menu.showMenu(this, actions, display);
		}
		else {
			return new PlayerDiedAction(spawnLocation);
		}
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
	public boolean addSouls(int souls) {
		this.setSouls(max(getSouls()+souls, 0));
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void resetInstance() {
		this.increaseMaxHp(0);
		for (Item item : this.getInventory()) {
			if (item.hasCapability(Abilities.DRINKABLE)) {
				((PotionItem) item).increaseCharges(((PotionItem) item).getMaxCharges());
				break;
			}
		}
	}

	@Override
	public boolean isExist() {
		return true;
	}
}
