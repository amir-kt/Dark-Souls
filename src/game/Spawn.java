package game;

import engine.*;
import game.enemies.LordOfCinder;
import game.enemies.Skeleton;
import game.ground.Cemetery;
import game.ground.Dirt;
import game.weapons.Broadsword;
import game.weapons.DarkmoonLongbow;
import game.weapons.StormRuler;
import game.weapons.YhormsGreatMachete;

import java.util.Random;

public class Spawn {

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private GameMap map;
    private Random rand = new Random();

    // A system to generate random locations to place cemeteries, skeletons, and other enemies every time the game starts.

    /**
     *
     * @param map to place actors and cemeteries on
     */
    public Spawn(GameMap map){

        NumberRange xRange = map.getXRange();
        xMin = xRange.min();
        xMax = xRange.max();

        NumberRange yRange = map.getYRange();
        yMin = yRange.min();
        yMax = yRange.max();

        this.map = map;
    }

    /**
     *
     * @param world to add player to
     */
    public void spawnPlayer(World world){

        Actor player = new Player("Unkindled", '@', AppConfig.PLAYER_HIT_POINTS,
                map.at(AppConfig.PLAYER_SPAWN_POSITION.getX(),AppConfig.PLAYER_SPAWN_POSITION.getY()));

        player.addItemToInventory(new Broadsword());
        player.addItemToInventory(new EstusFlask());

        world.addPlayer(player, map.at(AppConfig.PLAYER_SPAWN_POSITION.getX(),AppConfig.PLAYER_SPAWN_POSITION.getY()));
    }

    /**
     *
     * @param num of skeleton to spawn
     */
    public void spawnSkeleton(int num){

        for (int i = 0; i < num; i++){

            Location loc = randomLocation();

            Actor skeleton = new Skeleton(loc.x() , loc.y());
            skeleton.addItemToInventory(new Broadsword());

            map.at(loc.x(), loc.y()).addActor(skeleton);

        }
    }

    /**
     *
     * @param num of cemeteries to place on the map
     */
    public void spawnCemetery(int num){

        for (int i = 0; i < num; i++){

            Location loc = randomLocation();

            map.at(loc.x(), loc.y()).setGround(new Cemetery());

        }
    }

    /**
     *
     */
    public void spawnYhorm(){

        Actor boss = new LordOfCinder("Yhorm the Giant", 'Y', AppConfig.YHORM_MAX_HIT_POINTS);

        boss.addItemToInventory(new YhormsGreatMachete());

        map.at(AppConfig.YHORM_SPAWN_POSITION.getX(),AppConfig.YHORM_SPAWN_POSITION.getY()).addActor(boss);
    }

    /**
     *
     */
    public void spawnProfaneWeapons(){
        map.at(AppConfig.STORM_RULER_POSITION.getX(),AppConfig.STORM_RULER_POSITION.getY()).addItem(new StormRuler());
    }

    /**
     *
     */
    public void spawnAldrich(){

        Actor boss = new LordOfCinder("Aldrich the Devourer", 'A', AppConfig.ALDRICH_MAX_HIT_POINTS);

        boss.addItemToInventory(new DarkmoonLongbow());

        map.at(AppConfig.ALDRICH_SPAWN_POSITION.getX(),AppConfig.ALDRICH_SPAWN_POSITION.getY()).addActor(boss);
    }

    /**
     *
     * @return Location for position on the map that is dirt and generated randomly
     */
    private Location randomLocation(){

        int x = rand.nextInt(xMax - xMin) + xMin;
        int y = rand.nextInt(yMax - yMin) + yMin;

        if (testLocation(x,y)){
            return new Location(map, x, y);
        }

        return randomLocation();
    }

    /**
     *
     * @param x x-coorodinate on map
     * @param y y-coordinate on map
     * @return valid place to put actor / cemetery (ground in that spot on the map is dirt)
     */
    private boolean testLocation(int x, int y){

        return map.at(x, y).getGround() instanceof Dirt;
    }

}
