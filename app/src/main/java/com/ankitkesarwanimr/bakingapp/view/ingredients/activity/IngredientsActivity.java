package com.ankitkesarwanimr.bakingapp.view.ingredients.activity;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.ankitkesarwanimr.bakingapp.R;
import com.ankitkesarwanimr.bakingapp.databinding.ActivityIngredientsBinding;
import com.ankitkesarwanimr.bakingapp.helper.Constants;
import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;
import com.ankitkesarwanimr.bakingapp.presenter.ingredients.activity.IngredientsActivityPresenter;
import com.ankitkesarwanimr.bakingapp.presenter.ingredients.activity.IngredientsActivityPresenterImpl;
import com.ankitkesarwanimr.bakingapp.util.ConfigurationDetector;
import com.ankitkesarwanimr.bakingapp.view.ingredients.fragment.IngredientsFragment;

import java.util.Objects;

public class IngredientsActivity extends AppCompatActivity implements IngredientsActivityView {

    private ActivityIngredientsBinding mActivityIngredientsBinding;
    private IngredientsActivityPresenter mIngredientsActivityPresenter;
    private RecipeResponse mRecipeResponse;
    private int mWhichItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityIngredientsBinding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients);
        mIngredientsActivityPresenter = new IngredientsActivityPresenterImpl(this);
        mIngredientsActivityPresenter.invokeFirstRun();

        if (savedInstanceState == null) {
            mIngredientsActivityPresenter.attachFragment();
        }

        int mOrientation = ConfigurationDetector.isTabletInLandscapeMode(this);

        if (getResources().getBoolean(R.bool.isTablet) && mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }
    }

    @Override
    public void onFirstRun() {
        mRecipeResponse = getIntent().getParcelableExtra(Constants.RECIPE_MODEL_INTENT_EXTRA);
        mWhichItem = getIntent().getIntExtra(Constants.RECIPE_MODEL_STEPS_ID_INTENT_EXTRA, 0);

        setSupportActionBar(Objects.requireNonNull(mActivityIngredientsBinding.lIngredientsToolbar).toolbar);
        setTitle("");
        mActivityIngredientsBinding.lIngredientsToolbar.tvToolbarHeader.setText(String.format(getString(R.string.ingredients_header),
                mRecipeResponse.getName()));

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onFragmentAttached() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flIngredientsHolder, IngredientsFragment.getInstance(mRecipeResponse, mWhichItem))
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mIngredientsActivityPresenter = new IngredientsActivityPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        mIngredientsActivityPresenter.destroyView();

        super.onDestroy();
    }
}