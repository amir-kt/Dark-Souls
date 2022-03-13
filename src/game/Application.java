package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.*;
import game.ground.*;
import game.helpers.BonfireHelper;


/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley() ,new Cemetery(), new Bonfire(), new Door());

		List<String> profaneCapitalMap = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"........................................................................++......",
				".........................................................................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				".............................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#___B___#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+......................................................++.........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........",
				"++...+++.........................................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............................+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.......=.....................................++");

		GameMap gameMapProfane = new GameMap(groundFactory, profaneCapitalMap);
		world.addGameMap(gameMapProfane);

		//spawn Player, enemies and cemeteries
		Spawn profaneSpawn = new Spawn(gameMapProfane);
		profaneSpawn.spawnPlayer(world);
		profaneSpawn.spawnSkeleton(9);
		profaneSpawn.spawnCemetery(5);
		profaneSpawn.spawnYhorm();
		profaneSpawn.spawnProfaneWeapons();

		// giving a name to firelink shrine bonfire
		Bonfire firelinkShrine = (Bonfire) gameMapProfane.at(38,11).getGround();
		firelinkShrine.setName("Firelink Shrine");
		firelinkShrine.setLit(true);







//		anor londo map is created below:
		List<String> anorLondoMap = Arrays.asList(
				"......++.+++..+++......+....=...........................",
				"............+++++................B......................",
				"...............+++......................................",
				"........................................................",
				"........................................................",
				"................................+.................+.....",
				".................................+++...............+++..",
				".............++++++..............++.+..............++...",
				"........................................................",
				"..................##__###############....++.............",
				".................#..................#...................",
				"...............++#..................#...................",
				".............+++.#..................#...................",
				"................+##__################...................",
				"..................+.....................................");

		GameMap gameMapAnor = new GameMap(groundFactory, anorLondoMap);
		world.addGameMap(gameMapAnor);

		//spawn enemies and cemeteries
		Spawn anorSpawn = new Spawn(gameMapAnor);
		anorSpawn.spawnSkeleton(3);
		anorSpawn.spawnCemetery(2);
		anorSpawn.spawnAldrich();


		// giving a name to Anor Londo Bonfire
		Bonfire anorLondoBonfire = (Bonfire) gameMapAnor.at(33,1).getGround();
		anorLondoBonfire.setName("Anor Londo");



		// creating the fog door locations
		Location profaneFogDoorLoc = gameMapProfane.at(AppConfig.PROFANE_CAPITAL_FOG_DOOR_LOC.getX(), AppConfig.PROFANE_CAPITAL_FOG_DOOR_LOC.getY());
		Location anorFogDoorLoc = gameMapAnor.at(AppConfig.ANOR_LONDO_FOG_DOOR_LOC.getX(), AppConfig.ANOR_LONDO_FOG_DOOR_LOC.getY());

		// creating the fog door on the profane capital map
		Door profaneFogDoor = (Door) profaneFogDoorLoc.getGround();
		profaneFogDoor.setDestination(anorFogDoorLoc);
		profaneFogDoor.setDestName("Profane Capital");
		profaneFogDoorLoc.setGround(profaneFogDoor);

		// creating the fog door on the anor londo map
		Door AnorFogDoor = (Door) anorFogDoorLoc.getGround();
		AnorFogDoor.setDestination(profaneFogDoorLoc);
		AnorFogDoor.setDestName("Anor Londo");
		anorFogDoorLoc.setGround(AnorFogDoor);

		// finding all the bonfires on the maps
		ArrayList<Location> bonfires = BonfireHelper.findBonfires(gameMapProfane);
		bonfires.addAll(BonfireHelper.findBonfires(gameMapAnor));

		firelinkShrine.setBonfires(bonfires);
		anorLondoBonfire.setBonfires(bonfires);




		world.run();

	}
}
