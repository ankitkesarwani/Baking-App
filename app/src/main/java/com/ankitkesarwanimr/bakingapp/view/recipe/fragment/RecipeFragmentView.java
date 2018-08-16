package com.ankitkesarwanimr.bakingapp.view.recipe.fragment;

import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;

import java.util.List;

/**
 * Created by ankitkesarwanimr on 3/13/18.
 */

public interface RecipeFragmentView {
    void onRecipesLoaded(List<RecipeResponse> recipeResponses);

    void onRecipesLoadingFailed(String message);

    void onScreenRotated();

    void onProgressVisibility(boolean visible);
}
