package com.ankitkesarwanimr.bakingapp.view.recipe.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ankitkesarwanimr.bakingapp.R;
import com.ankitkesarwanimr.bakingapp.callback.IErrorCallbackListener;
import com.ankitkesarwanimr.bakingapp.databinding.ActivityRecipeBinding;
import com.ankitkesarwanimr.bakingapp.helper.Constants;
import com.ankitkesarwanimr.bakingapp.presenter.recipe.activity.RecipeActivityPresenter;
import com.ankitkesarwanimr.bakingapp.presenter.recipe.activity.RecipeActivityPresenterImpl;
import com.ankitkesarwanimr.bakingapp.util.ConnectionSniffer;
import com.ankitkesarwanimr.bakingapp.view.about.AboutActivity;
import com.ankitkesarwanimr.bakingapp.view.other.ErrorFragment;
import com.ankitkesarwanimr.bakingapp.view.recipe.fragment.RecipeFragment;

public class RecipeActivity extends AppCompatActivity implements RecipeActivityView, IErrorCallbackListener {

    private ActivityRecipeBinding mActivityRecipeBinding;
    private RecipeActivityPresenter mRecipeActivityPresenter;

    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRecipeBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);

        mRecipeActivityPresenter = new RecipeActivityPresenterImpl(this);
        mRecipeActivityPresenter.invokeFirstRun();

        if (savedInstanceState == null) {
            mRecipeActivityPresenter.attachFragment();
        }
    }

    @Override
    public void onFragmentAttached() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flRecipesHolder, RecipeFragment.getInstance(this))
                .commit();
    }

    @Override
    public void onFirstRun() {
        setSupportActionBar(mActivityRecipeBinding.lRecipeToolbar.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClicked = item.getItemId();

        switch (itemThatWasClicked) {
            /*case R.id.main_about:
                Intent aboutIntent = new Intent(RecipeActivity.this, AboutActivity.class);
                startActivityForResult(aboutIntent, Constants.CHILD_ACTIVITY_REQUEST_CODE);
                return true;*/

            case R.id.main_rate_now:
                Toast.makeText(RecipeActivity.this, "Now available in Play Store", Toast.LENGTH_LONG).show();

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int timeDelay = getResources().getInteger(R.integer.delay_in_seconds_to_close);

        if (mBackPressed + timeDelay > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.twice_press_to_exit),
                    Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecipeActivityPresenter = new RecipeActivityPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        mRecipeActivityPresenter.destroyView();
        super.onDestroy();
    }

    @Override
    public void onErrorScreenShown(boolean visible) {
        if (visible) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flRecipesHolder, ErrorFragment.getInstance(this))
                    .commit();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }

    @Override
    public void onMainScreenShown(boolean visible) {
        boolean isConnected = ConnectionSniffer.sniff(Constants.ENTIRE_URL);

        if (visible && isConnected) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flRecipesHolder, RecipeFragment.getInstance(this))
                    .commit();
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}