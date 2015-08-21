package mknutsen.aiproject;

import java.awt.image.BufferedImage;

public final class Config {


    // GENERAL PLAYER
    public static final long VELOCITY_SLEEP_AMOUNT = 30;

    public static final int BULLET_STUN_TIME = 0;

    public static final int BULLET_COOLDOWN_TIME = 200;

    public static final int SHIELD_COOLDOWN_TIME = 500;

    public static final int SHIELD_DURATION = 1000;

    public static final int SHIELD_STUN_TIME = 0;

    public static final int BULLET_SPEED_PER_10_MS = 7;

    public static final int STARTING_HEALTH = 10;

    public static final int HIT_STUN_TIME = 0;

    public static final int DEFAULT_PLAYER_WIDTH = 20;

    public static final int DEFAULT_PLAYER_HEIGHT = 230;

    public static final int MOVE_PER_10_MS = 15;

    // MOUSE PLAYER
    // KB PLAYER
    public static final int KB_MOVE_PER_CLICK = 200;

    // GENERAL AI
    public static final long AI_CHILL_TIME = 10;

    public static final int SHIELD_MULTIPLIER_FOR_SAFETY = 3;

    // GENERAL GAME
    public static final int P_ONE_X = 5;

    public static final int P_TWO_X = 845 - DEFAULT_PLAYER_WIDTH;
    // DECISION TREE

    public static final int DEFAULT_Y = 0;

    public static final boolean DEBUG = false;

    public static final int BULLET_HEIGHT = 10;

    public static final int BULLET_WIDTH = 50;

    public static final int BULLET_VERTICAL_OFFSET = 50;

    public static final int KB_THRESHOLD = 5;

    public static BufferedImage[] BULLET_IMAGES = new BufferedImage[2];

    public static BufferedImage[] PLAYER_IMAGES = new BufferedImage[2];

    private Config() {

    }
}
