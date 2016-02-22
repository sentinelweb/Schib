package uk.co.sentinelweb.schibsted.model;

/**
 * Created by robert on 21/02/16.
 */
public class Recipe {
    private String publisher;
    private String f2f_url;
    private String title;
    private String source_url;
    private String recipe_id;
    private String image_url;
    private Double social_rank;
    private String publisher_url;

    public String getF2f_url() {
        return f2f_url;
    }

    public void setF2f_url(final String f2f_url) {
        this.f2f_url = f2f_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(final String image_url) {
        this.image_url = image_url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(final String publisher_url) {
        this.publisher_url = publisher_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(final String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Double getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(final Double social_rank) {
        this.social_rank = social_rank;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(final String source_url) {
        this.source_url = source_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
