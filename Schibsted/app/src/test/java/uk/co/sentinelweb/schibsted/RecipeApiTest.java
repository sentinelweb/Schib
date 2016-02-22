package uk.co.sentinelweb.schibsted;

import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;
import uk.co.sentinelweb.schibsted.net.Services;
import uk.co.sentinelweb.schibsted.net.call.RecipeResponse;
import uk.co.sentinelweb.schibsted.net.services.RecipeService;

/**
 * Created by robert on 28/11/15.
 */
public class RecipeApiTest {

    @Test
    @LargeTest
    public void shouldLoadRecipes() {
        final RecipeService recipeService = new Services().getRecipeService();
        final Call<RecipeResponse> gameListCall = recipeService.listRecipes("b549c4c96152e677eb90de4604ca61a2", null, null, null);
        RecipeResponse recipeList = null;
        try {
            final Response<RecipeResponse> response = gameListCall.execute();
            recipeList = response.body();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue("call failed:"+e.getMessage(), false);
        }
        Assert.assertNotNull("No response",recipeList);
        Assert.assertTrue("recipeList list empty", recipeList.getCount()>0);
        Assert.assertTrue("recipeList list empty", recipeList.getRecipes().size()>0);
    }

}
