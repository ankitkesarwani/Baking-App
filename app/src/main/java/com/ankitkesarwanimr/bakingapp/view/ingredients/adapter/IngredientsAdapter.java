package com.ankitkesarwanimr.bakingapp.view.ingredients.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ankitkesarwanimr.bakingapp.BR;
import com.ankitkesarwanimr.bakingapp.R;
import com.ankitkesarwanimr.bakingapp.databinding.IngredientsItemCardBinding;
import com.ankitkesarwanimr.bakingapp.helper.Constants;
import com.ankitkesarwanimr.bakingapp.model.Ingredients;

import java.util.List;

import timber.log.Timber;

/**
 * Created by ankitkesarwanimr on 8/15/18.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredients> mIngredients;

    public IngredientsAdapter(List<Ingredients> mIngredients) {
        this.mIngredients = mIngredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        IngredientsItemCardBinding ingredientsItemCardBinding = DataBindingUtil.inflate(inflater,
                R.layout.ingredients_item_card,
                parent,
                false);

        return new ViewHolder(ingredientsItemCardBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredients ingredients = mIngredients.get(position);

        holder.bindViews(ingredients);
        holder.setCustomCardViewWidth();
    }

    public void swapData() {
        if (mIngredients.size() == 0)
            return;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private IngredientsItemCardBinding mIngredientsItemBinding;

        ViewHolder(View itemView) {
            super(itemView);

            mIngredientsItemBinding = DataBindingUtil.bind(itemView);
        }

        void bindViews(Ingredients ingredients) {
            mIngredientsItemBinding.setVariable(BR.ingredients, ingredients);
            mIngredientsItemBinding.executePendingBindings();
        }

        void setCustomCardViewWidth() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) mIngredientsItemBinding.getRoot().getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            ViewGroup.LayoutParams layoutParams = mIngredientsItemBinding.cvIngredients.getLayoutParams();

            if (getItemCount() == 1) {
                return;
            }

            if (displayMetrics.widthPixels <= Constants.SCREEN_WIDTH_PIXEL_CONDITION) {
                layoutParams.width = (int) (displayMetrics.widthPixels / (1.5));
            } else {
                layoutParams.width = (int) (displayMetrics.widthPixels / (2.5));
            }

            Timber.d(String.valueOf(displayMetrics.widthPixels));
            mIngredientsItemBinding.cvIngredients.setLayoutParams(layoutParams);
        }
    }
}