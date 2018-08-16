package com.ankitkesarwanimr.bakingapp.util;

import android.content.Context;

/**
 * Created by ankitkesarwanimr on 8/16/18.
 */

public class ConfigurationDetector {

    public static int isTabletInLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation;
    }
}