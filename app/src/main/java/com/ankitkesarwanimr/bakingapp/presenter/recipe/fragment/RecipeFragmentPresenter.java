package com.ankitkesarwanimr.bakingapp.presenter.recipe.fragment;

/**
 * Created by ankitkesarwanimr on 8/13/18.
 */

public interface RecipeFragmentPresenter {
    void fetchRecipes();

    void behaveAfterRotation();

    void destroyView();
}
