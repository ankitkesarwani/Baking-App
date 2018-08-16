package com.ankitkesarwanimr.bakingapp.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ankitkesarwanimr.bakingapp.presenter.splash.SplashActivityPresenter;
import com.ankitkesarwanimr.bakingapp.presenter.splash.SplashActivityPresenterImpl;
import com.ankitkesarwanimr.bakingapp.view.recipe.activity.RecipeActivity;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {

    private SplashActivityPresenter mSplashActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashActivityPresenter = new SplashActivityPresenterImpl(this);
        mSplashActivityPresenter.openActivity();
    }

    @Override
    public void onActivityOpened() {
        Intent recipeIntent = new Intent(SplashActivity.this, RecipeActivity.class);
        recipeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        recipeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(recipeIntent);
    }

    @Override
    protected void onDestroy() {
        mSplashActivityPresenter.destroyView();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashActivityPresenter = new SplashActivityPresenterImpl(this);
    }
}