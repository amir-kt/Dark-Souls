package game.actions;

import engine.Actor;
import engine.Exit;
import engine.GameMap;
import engine.Location;
import game.TokenOfSouls;
import game.ground.Valley;

public class PlayerDiedAction extends SoftResetAction {
    Location spawnLocation;

    public PlayerDiedAction(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        // drop token of souls and move player back to spawn point
        TokenOfSouls tokenOfSouls = new TokenOfSouls();

        // making sure the token of souls is dropping at a location where the player can retrieve it later
        // and it is not a valley
        if (map.locationOf(actor).getGround() instanceof Valley) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                if (!(exit.getDestination().getGround() instanceof Valley &&
                        exit.getDestination().getGround().canActorEnter(actor))) {
                    exit.getDestination().addItem(tokenOfSouls);
                    break;
                }
            }
        }
        else {
            map.locationOf(actor).addItem(tokenOfSouls);
        }

        actor.asSoul().transferSouls(tokenOfSouls.asSoul());

        map.moveActor(actor, spawnLocation);

        super.execute(actor, map);

        return """
                               ...
                             ;::::;
                           ;::::; :;
                         ;:::::'   :;
                        ;:::::;     ;.
                       ,:::::'       ;           OOO\\
                       ::::::;       ;          OOOOO\\
                       ;:::::;       ;         OOOOOOOO
                      ,;::::::;     ;'         / OOOOOOO
                    ;:::::::::`. ,,,;.        /  / DOOOOOO
                  .';:::::::::::::::::;,     /  /     DOOOO
                 ,::::::;::::::;;;;::::;,   /  /        DOOO
                ;`::::::`'::::::;;;::::: ,#/  /          DOOO
                :`:::::::`;::::::;;::: ;::#  /            DOOO
                ::`:::::::`;:::::::: ;::::# /              DOO
                `:`:::::::`;:::::: ;::::::#/               DOO
                 :::`:::::::`;; ;:::::::::##                OO
                 ::::`:::::::`;::::::::;:::#                OO
                 `:::::`::::::::::::;'`:;::#                O
                  `:::::`::::::::;' /  / `:#
                   ::::::`:::::;'  /  /   `#
                YOU DIED
                """;

    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
