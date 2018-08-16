package com.ankitkesarwanimr.bakingapp.presenter.splash;

import android.os.Handler;

import com.ankitkesarwanimr.bakingapp.helper.Constants;
import com.ankitkesarwanimr.bakingapp.view.splash.SplashActivityView;


/**
 * Created by ankitkesarwanimr on 8/13/18.
 */

public class SplashActivityPresenterImpl implements SplashActivityPresenter {

    private SplashActivityView mSplashActivityView;

    public SplashActivityPresenterImpl(SplashActivityView mSplashActivityView) {
        this.mSplashActivityView = mSplashActivityView;
    }

    @Override
    public void openActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashActivityView.onActivityOpened();
            }
        }, Constants.ACTIVITY_OPEN_DURATION);
    }

    @Override
    public void destroyView() {
        if (mSplashActivityView != null) {
            mSplashActivityView = null;
        }
    }
}