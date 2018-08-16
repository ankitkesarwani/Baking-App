package com.ankitkesarwanimr.bakingapp.helper;

/**
 * Created by ankitkesarwanimr on 8/13/18.
 */

public class Constants {

    public static final int ACTIVITY_OPEN_DURATION = 2000;
    public static final int NETWORK_TIMEOUT_DURATION = 70000;
    public static final String RECIPE_MODEL_STATE = "recipe-model";
    public static final String PROGRESS_RECIPE_VISIBILITY_STATE = "progress-state-visibility-model";
    public static final String RECIPE_MODEL_INTENT_EXTRA = "recipe-model-intent-extra";
    public static final String RECIPE_MODEL_STEPS_ID_INTENT_EXTRA = "recipe-model-video-id-intent-extra";
    public static final int CHILD_ACTIVITY_REQUEST_CODE = 1453;
    public static final int MILLISECOND_TO_BLUR = 600;
    public static final int RADIUS_BLUR = 10;
    public static final int SAMPLING_BLUR = 2;
    public static final int ANIMATE_BLUR = 1000;
    public static final String EXO_PLAYER_USER_AGENT = "ExoPlayerAgent";
    public static final String NUTELLA_PIE_CASE = "Nutella Pie";
    public static final String BROWNIES_CASE = "Brownies";
    public static final String YELLOW_CAKE_CASE = "Yellow Cake";
    public static final String CHEESECAKE_CASE= "Cheesecake";
    public static final int TABLET_DIVIDER = 1000;
    public static final String EXO_PLAYER_VIDEO_POSITION = "exo-player-video-position";
    public static final String EXO_PLAYER_PLAYING_STATE = "exo-player-playing-state";
    public static final int SCREEN_WIDTH_PIXEL_CONDITION = 1440;
    public static final String FRAGMENT_ORDER = "fragment-order";
    public static final String DATABASE_NAME = "mybaking.db";
    public static final String STEP_COUNT_STATE = "step-count-state";
    public static final String LAST_STEP_CLICKED_STATE = "last-step-clicked-state";
    public static final int EXO_PLAYER_VIDEO_CACHE_DURATION = 10 * 1024 * 1024;
    public static final String ENTIRE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public Constants() {
        throw new AssertionError();
    }
}
