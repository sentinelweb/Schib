package uk.co.sentinelweb.schibsted.loader;

import android.content.Context;
import android.os.Bundle;

import junit.framework.Assert;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;
import uk.co.sentinelweb.schibsted.android.ListActivity;
import uk.co.sentinelweb.schibsted.android.RecipeApplication;
import uk.co.sentinelweb.schibsted.net.Services;
import uk.co.sentinelweb.schibsted.net.call.RecipeResponse;
import uk.co.sentinelweb.schibsted.net.services.RecipeService;

/**
 * Created by robert on 21/02/16.
 */
public class RecipeLoader extends LoaderParent<RecipeResponse> {
    Bundle args;
    public RecipeLoader(final Context context) {
        super(context);
    }

    @Override
    public RecipeResponse loadInBackground() {
        final RecipeService recipeService = new Services((RecipeApplication)getContext().getApplicationContext()).getRecipeService();
        String q = null;
        if (args!=null) {
            q=args.getString(ListActivity.ARG_Q);
        }
        final Call<RecipeResponse> gameListCall = recipeService.listRecipes("b549c4c96152e677eb90de4604ca61a2", q, null, null);

        RecipeResponse recipeList = null;
        try {
            final Response<RecipeResponse> response = gameListCall.execute();
            recipeList = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Bundle getArgs() {
        return args;
    }

    public void setArgs(final Bundle args) {
        this.args = args;
    }
}
