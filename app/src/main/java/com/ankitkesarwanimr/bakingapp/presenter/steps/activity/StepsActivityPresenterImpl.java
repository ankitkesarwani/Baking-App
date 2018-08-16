package com.ankitkesarwanimr.bakingapp.presenter.steps.activity;

import com.ankitkesarwanimr.bakingapp.view.steps.activity.StepsActivityView;

/**
 * Created by ankitkesarwanimr on 8/14/18.
 */

public class StepsActivityPresenterImpl implements StepsActivityPresenter {

    private StepsActivityView mStepsActivityView;

    public StepsActivityPresenterImpl(StepsActivityView mStepsActivityView) {
        this.mStepsActivityView = mStepsActivityView;
    }

    @Override
    public void invokeFirstRun() {
        mStepsActivityView.onFirstRun();
    }

    @Override
    public void attachFragment() {
        mStepsActivityView.onFragmentAttached();
    }

    @Override
    public void destroyView() {
        if (mStepsActivityView != null) {
            mStepsActivityView = null;
        }
    }
}