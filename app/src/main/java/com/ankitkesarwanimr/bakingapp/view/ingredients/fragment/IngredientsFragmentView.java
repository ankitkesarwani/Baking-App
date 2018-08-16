package com.ankitkesarwanimr.bakingapp.view.ingredients.fragment;

/**
 * Created by ankitkesarwanimr on 8/14/18.
 */

public interface IngredientsFragmentView {
    void onIngredientsLoaded();

    void onPlayerInitialized();

    void onPlayerReleased();

    void onItemsAddedInDatabase();

    void onItemsRemovedFromDatabase();
}
