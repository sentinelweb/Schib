package uk.co.sentinelweb.schibsted.net.services;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import uk.co.sentinelweb.schibsted.net.call.RecipeResponse;

public interface RecipeService {

    // obviously user isn't needed for this example it just to show usage
    @GET("search")
    Call<RecipeResponse> listRecipes(@Query("key") String key, @Query("q") String query, @Query("sort") String sort, @Query("page") Integer page);
}