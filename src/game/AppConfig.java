package game;

public final class AppConfig {
    private AppConfig() {}

    // player configs
    public final static int PLAYER_HIT_POINTS = 200;
    public final static Position PLAYER_SPAWN_POSITION = new Position(38,12);
    public final static int PLAYER_STARTING_SOULS = 0;
    public final static int PLAYER_BLINK_DISTANCE = 4;

    // Yhorm the giant configs
    public final static int YHORM_MAX_HIT_POINTS = 500;
    public final static Position YHORM_SPAWN_POSITION = new Position(6, 25);
    public final static int LORD_OF_CINDER_SOULS = 5000;

    // Undead configs
    public final static int UNDEAD_MAX_HIT_POINTS = 50;
    public final static int UNDEAD_SOULS = 50;
    public final static int CEMETERY_SPAWN_UNDEAD_CHANCE = 25;

    // skeleton configs
    public final static int SKELETON_MAX_HIT_POINTS = 100;
    public final static int SKELETON_SOULS = 250;

    // broadsword configs
    public final static int BROADSWORD_DAMAGE = 30;
    public final static int BROADSWORD_HIT_RATE = 80;
    public final static int BROADSWORD_CRITICAL_HIT = 20;

    // storm ruler configs
    public final static int STORM_RULER_DAMAGE = 70;
    public final static int STORM_RULER_HIT_RATE = 60;
    public final static int STORM_RULER_CHANCE_DOUBLE_DAMAGE = 20;
    public final static int STORM_RULER_ACTIVE_SKILL_CHARGES = 3;
    public final static int STORM_RULER_WIND_SLASH_DAMAGE_MULTIPLICITY = 2;
    public final static int STORM_RULER_WIND_SLASH_HIT_RATE = 100;
    public final static Position STORM_RULER_POSITION = new Position(7, 25);


    // door configs
    public final static Position PROFANE_CAPITAL_FOG_DOOR_LOC = new Position(40, 25);
    public final static Position ANOR_LONDO_FOG_DOOR_LOC = new Position(28, 0);

    public final static int YHORMS_GREAT_MACHETE_DAMAMGE = 95;
    public final static int YHORMS_GREAT_MACHETE_HIT_RATE = 60;
    public final static int YHORMS_GREAT_MACHETE_EMBER_FORM_HIT_RATE_INCREASE = 30;

    // aldrich the destroyer configs
    public final static int ALDRICH_MAX_HIT_POINTS = 350;
    public final static Position ALDRICH_SPAWN_POSITION = new Position(30,12);

    // darkmoon longbow configs
    public final static int DARKMOON_DAMAGE = 70;
    public final static int DARKMOON_HIT_RATE = 80;
    public final static int DARKMOON_CRITICAL_HIT = 15;
}
