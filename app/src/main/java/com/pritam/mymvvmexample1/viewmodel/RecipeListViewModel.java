package com.pritam.mymvvmexample1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pritam.mymvvmexample1.models.Recipe;
import com.pritam.mymvvmexample1.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private boolean mIsViewingRecipes;
    private boolean mIsPerformingQuery;
    private RecipeRepository mRecipeRepository;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeRepository.getRecipes();
    }

    public LiveData<String> getError(){
        return mRecipeRepository.getError();
    }

    public LiveData<Boolean> isQueryExhausted(){
        return mRecipeRepository.isQueryExhausted();
    }

    public boolean isViewingRecipes(){
        return mIsViewingRecipes;
    }

    public void setIsViewingRecipes(boolean isViewingRecipes){
        mIsViewingRecipes = isViewingRecipes;
    }

    public void setIsPerformingQuery(Boolean isPerformingQuery){
        mIsPerformingQuery = isPerformingQuery;
    }

    public boolean isPerformingQuery(){
        return mIsPerformingQuery;
    }


    public boolean onBackPressed(){
        if(mIsPerformingQuery){
            // cancel the query
            mRecipeRepository.cancelRequest();
            mIsPerformingQuery = false;
        }
        if(mIsViewingRecipes){
            mIsViewingRecipes = false;
            return false;
        }
        return true;
    }

    public void searchRecipesApi(String query, int pageNumber){
        mIsViewingRecipes = true;
        mIsPerformingQuery = true;
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage(){
        if(!mIsPerformingQuery
                && mIsViewingRecipes
                && !isQueryExhausted().getValue()){
            mRecipeRepository.searchNextPage();
        }
    }

}
