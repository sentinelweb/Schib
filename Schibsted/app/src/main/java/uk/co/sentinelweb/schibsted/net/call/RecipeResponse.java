package uk.co.sentinelweb.schibsted.net.call;

import java.util.List;

import uk.co.sentinelweb.schibsted.model.Recipe;

/**
 * Created by robert on 21/02/16.
 */
public class RecipeResponse {
    int count;
    List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(final List<Recipe> list) {
        this.recipes = list;
    }
}
