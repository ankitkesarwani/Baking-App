package com.ankitkesarwanimr.bakingapp.helper;

import com.ankitkesarwanimr.bakingapp.network.IBakingAPI;
import com.ankitkesarwanimr.bakingapp.model.RecipeResponse;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;


/**
 * Created by ankitkesarwanimr on 8/12/18.
 */

public class ObservableHelper {

    private static IBakingAPI getIBakingAPI() {
        return getRetrofit().create(IBakingAPI.class);
    }

    private static Retrofit getRetrofit() {
        return RetrofitInterceptor.build();
    }

    public static Observable<List<RecipeResponse>> getRecipes() {
        IBakingAPI mIBakingAPI = getIBakingAPI();

        return mIBakingAPI.getRecipes();
    }
}