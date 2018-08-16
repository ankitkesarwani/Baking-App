package com.ankitkesarwanimr.bakingapp.presenter.ingredients.fragment;

import com.ankitkesarwanimr.bakingapp.view.ingredients.fragment.IngredientsFragmentView;

/**
 * Created by ankitkesarwanimr on 8/14/18.
 */

public class IngredientsFragmentPresenterImpl implements IngredientsFragmentPresenter {

    private IngredientsFragmentView mIngredientsFragmentView;

    public IngredientsFragmentPresenterImpl(IngredientsFragmentView mIngredientsFragmentView) {
        this.mIngredientsFragmentView = mIngredientsFragmentView;
    }

    @Override
    public void loadIngredients() {
        mIngredientsFragmentView.onIngredientsLoaded();
    }

    @Override
    public void initializePlayer() {
        mIngredientsFragmentView.onPlayerInitialized();
    }

    @Override
    public void releasePlayer() {
        mIngredientsFragmentView.onPlayerReleased();
    }

    @Override
    public void addItemsInDatabase() {
        mIngredientsFragmentView.onItemsAddedInDatabase();
    }

    @Override
    public void removeItemsFromDatabase() {
        mIngredientsFragmentView.onItemsRemovedFromDatabase();
    }

    @Override
    public void destroyView() {
        if (mIngredientsFragmentView != null) {
            mIngredientsFragmentView = null;
        }
    }
}