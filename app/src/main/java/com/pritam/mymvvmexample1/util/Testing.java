package com.pritam.mymvvmexample1.util;

import android.util.Log;

import com.pritam.mymvvmexample1.models.Recipe;

import java.util.List;

public class Testing {

    public static void printRecipes(List<Recipe>list, String tag){
        for(Recipe recipe: list){
            Log.d(tag, "onChanged: " + recipe.getTitle());
        }
    }
}
