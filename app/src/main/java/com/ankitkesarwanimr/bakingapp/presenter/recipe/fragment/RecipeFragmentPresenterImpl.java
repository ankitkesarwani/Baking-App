package com.ankitkesarwanimr.bakingapp.presenter.recipe.fragment;

import android.accounts.NetworkErrorException;

import com.ankitkesarwanimr.bakingapp.App;
import com.ankitkesarwanimr.bakingapp.R;
import com.ankitkesarwanimr.bakingapp.helper.ObservableHelper;
import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;
import com.ankitkesarwanimr.bakingapp.view.recipe.fragment.RecipeFragmentView;

import java.util.List;

import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ankitkesarwanimr on 8/13/18.
 */

public class RecipeFragmentPresenterImpl implements RecipeFragmentPresenter {

    private RecipeFragmentView mRecipeFragmentView;

    public RecipeFragmentPresenterImpl(RecipeFragmentView mRecipeFragmentView) {
        this.mRecipeFragmentView = mRecipeFragmentView;
    }

    @Override
    public void fetchRecipes() {
        final Observable<List<RecipeResponse>> getRecipes = ObservableHelper.getRecipes();

        mRecipeFragmentView.onProgressVisibility(true);

        getRecipes.subscribeOn(Schedulers.io())
                .retry(1)
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<RecipeResponse>>>() {
                    @Override
                    public Observable<? extends List<RecipeResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<RecipeResponse>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof NetworkErrorException) {
                            mRecipeFragmentView.onRecipesLoadingFailed(App.getInstance().getString(R.string.no_connection_found));
                        } else if (e instanceof HttpException) {
                            mRecipeFragmentView.onRecipesLoadingFailed(App.getInstance().getString(R.string.no_connection_found));
                        } else {
                            mRecipeFragmentView.onRecipesLoadingFailed(App.getInstance().getString(R.string.no_recipe_found));
                        }

                        mRecipeFragmentView.onProgressVisibility(false);
                    }

                    @Override
                    public void onNext(List<RecipeResponse> recipeResponses) {
                        if (recipeResponses.size() == 0) {
                            mRecipeFragmentView.onRecipesLoadingFailed(App.getInstance().getString(R.string.no_recipe_found));
                            mRecipeFragmentView.onProgressVisibility(false);
                            return;
                        }

                        mRecipeFragmentView.onRecipesLoaded(recipeResponses);
                        mRecipeFragmentView.onProgressVisibility(false);
                    }
                });
    }

    @Override
    public void behaveAfterRotation() {
        mRecipeFragmentView.onScreenRotated();
    }

    @Override
    public void destroyView() {
        if (mRecipeFragmentView != null) {
            mRecipeFragmentView = null;
        }
    }
}