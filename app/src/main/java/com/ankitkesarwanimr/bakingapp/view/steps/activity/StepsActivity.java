package com.ankitkesarwanimr.bakingapp.view.steps.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.ankitkesarwanimr.bakingapp.R;
import com.ankitkesarwanimr.bakingapp.callback.IStepTabletCallbackListener;
import com.ankitkesarwanimr.bakingapp.databinding.ActivityStepsBinding;
import com.ankitkesarwanimr.bakingapp.helper.Constants;
import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;
import com.ankitkesarwanimr.bakingapp.presenter.steps.activity.StepsActivityPresenter;
import com.ankitkesarwanimr.bakingapp.presenter.steps.activity.StepsActivityPresenterImpl;
import com.ankitkesarwanimr.bakingapp.util.ConfigurationDetector;
import com.ankitkesarwanimr.bakingapp.view.ingredients.activity.IngredientsActivity;
import com.ankitkesarwanimr.bakingapp.view.ingredients.fragment.IngredientsFragment;
import com.ankitkesarwanimr.bakingapp.view.other.TabletTutorialFragment;
import com.ankitkesarwanimr.bakingapp.view.steps.fragment.StepsFragment;

public class StepsActivity extends AppCompatActivity implements StepsActivityView, IStepTabletCallbackListener {

    private ActivityStepsBinding mActivityStepsBinding;
    private StepsActivityPresenter mStepsActivityPresenter;

    private int mOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityStepsBinding = DataBindingUtil.setContentView(this, R.layout.activity_steps);

        mStepsActivityPresenter = new StepsActivityPresenterImpl(this);
        mStepsActivityPresenter.invokeFirstRun();

        if (savedInstanceState == null) {
            mStepsActivityPresenter.attachFragment();
        }
    }

    @Override
    public void onFirstRun() {
        setSupportActionBar(mActivityStepsBinding.lStepsToolbar.toolbar);
        setTitle("");

        mOrientation = ConfigurationDetector.isTabletInLandscapeMode(this);

        if (getResources().getBoolean(R.bool.isTablet) && mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mActivityStepsBinding.lStepsToolbar.tvToolbarHeader.setText(getString(R.string.steps_tablet_header));
        } else if (getResources().getBoolean(R.bool.isTablet) && mOrientation == Configuration.ORIENTATION_PORTRAIT){
            mActivityStepsBinding.lStepsToolbar.tvToolbarHeader.setText(getString(R.string.steps_header));
        }else {
            mActivityStepsBinding.lStepsToolbar.tvToolbarHeader.setText(getString(R.string.steps_header));
        }

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       if (getResources().getBoolean(R.bool.isTablet) && mOrientation == Configuration.ORIENTATION_PORTRAIT){
            menu.clear();
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onFragmentAttached() {
        RecipeResponse recipeResponse = getIntent().getParcelableExtra(Constants.RECIPE_MODEL_INTENT_EXTRA);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.flStepsFragmentHolder, StepsFragment.getInstance(recipeResponse, this))
                .commit();
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
    protected void onDestroy() {
        mStepsActivityPresenter.destroyView();

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        mStepsActivityPresenter = new StepsActivityPresenterImpl(this);
        super.onResume();
    }

    @Override
    public void onIngredientsScreenOpened(RecipeResponse recipeResponse, int whichItem, boolean isTablet) {
        if (isTablet && mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flStepIngredientsHolder, IngredientsFragment.getInstance(recipeResponse, whichItem))
                    .commit();
        } else if (isTablet && mOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent stepsIntent = new Intent(StepsActivity.this, IngredientsActivity.class);
            stepsIntent.putExtra(Constants.RECIPE_MODEL_INTENT_EXTRA, recipeResponse);
            stepsIntent.putExtra(Constants.RECIPE_MODEL_STEPS_ID_INTENT_EXTRA, whichItem);

            startActivityForResult(stepsIntent, Constants.CHILD_ACTIVITY_REQUEST_CODE);
        } else {
            Intent stepsIntent = new Intent(StepsActivity.this, IngredientsActivity.class);
            stepsIntent.putExtra(Constants.RECIPE_MODEL_INTENT_EXTRA, recipeResponse);
            stepsIntent.putExtra(Constants.RECIPE_MODEL_STEPS_ID_INTENT_EXTRA, whichItem);

            startActivityForResult(stepsIntent, Constants.CHILD_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    public void onTutorialScreensActivated(boolean isTablet, int order) {
        if (isTablet && mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flStepIngredientsHolder, TabletTutorialFragment.getInstance(order))
                    .commit();
        }
    }
}