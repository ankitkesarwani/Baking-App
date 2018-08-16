package com.ankitkesarwanimr.bakingapp.callback;

import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;

/**
 * Created by ankitkesarwanimr on 8/13/18.
 */

public interface IStepTabletCallbackListener {
    void onIngredientsScreenOpened(RecipeResponse recipeResponse, int whichItem, boolean isTablet);

    void onTutorialScreensActivated(boolean isTablet, int order);
}
