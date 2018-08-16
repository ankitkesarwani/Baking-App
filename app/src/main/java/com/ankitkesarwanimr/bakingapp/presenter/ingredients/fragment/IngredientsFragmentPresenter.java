package com.ankitkesarwanimr.bakingapp.presenter.ingredients.fragment;

/**
 * Created by ankitkesarwanimr on 8/14/18.
 */

public interface IngredientsFragmentPresenter {
    void loadIngredients();

    void initializePlayer();

    void releasePlayer();

    void addItemsInDatabase();

    void removeItemsFromDatabase();

    void destroyView();
}
