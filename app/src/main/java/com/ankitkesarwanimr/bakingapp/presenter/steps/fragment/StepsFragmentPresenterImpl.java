package com.ankitkesarwanimr.bakingapp.presenter.steps.fragment;

import com.ankitkesarwanimr.bakingapp.view.steps.fragment.StepsFragmentView;

/**
 * Created by ankitkesarwanimr on 8/14/18.
 */

public class StepsFragmentPresenterImpl implements StepsFragmentPresenter {

    private StepsFragmentView mStepsFragmentView;

    public StepsFragmentPresenterImpl(StepsFragmentView mStepsFragmentView) {
        this.mStepsFragmentView = mStepsFragmentView;
    }

    @Override
    public void loadSteps() {
        mStepsFragmentView.onStepsLoaded();
    }

    @Override
    public void destroyView() {
        if (mStepsFragmentView != null) {
            mStepsFragmentView = null;
        }
    }
}