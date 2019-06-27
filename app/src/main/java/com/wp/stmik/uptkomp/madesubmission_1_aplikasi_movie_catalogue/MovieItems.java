package com.wp.stmik.uptkomp.madesubmission_1_aplikasi_movie_catalogue;

import org.json.JSONObject;

public class MovieItems {

    private String mov_title;
    private String mov_synopsis;
    private String mov_date;
    private String mov_image;
    private String mov_rate;
    private String mov_rate_count;
    private String mov_language;



    public MovieItems(JSONObject object){
        try {

            String title        = object.getString("title");
            String synopsis     = object.getString("overview");
            String release_date = object.getString("release_date");
            String image        = object.getString("poster_path");
            String rate_count   = object.getString("vote_count");
            String rate         = object.getString("vote_average");
            String language     = object.getString("original_language");



            this.mov_title          = title;
            this.mov_synopsis       = synopsis;
            this.mov_date           = release_date;
            this.mov_image          = image;
            this.mov_rate_count     = rate_count;
            this.mov_rate           = rate;
            this.mov_language       = language;


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getMov_title() {
        return mov_title;
    }

    public void setMov_title(String mov_title) {
        this.mov_title = mov_title;
    }

    public String getMov_synopsis() {
        return mov_synopsis;
    }

    public void setMov_synopsis(String mov_synopsis) { this.mov_synopsis = mov_synopsis; }

    public String getMov_date() {
        return mov_date;
    }

    public void setMov_date(String mov_date) {
        this.mov_date = mov_date;
    }

    public String getMov_image() {
        return mov_image;
    }

    public void setMov_image(String mov_image) { this.mov_image = mov_image; }

    public String getMov_rate_count() {
        return mov_rate_count;
    }

    public void setMov_rate_count(String mov_rate_count) {
        this.mov_rate_count = mov_rate_count;
    }

    public String getMov_rate() {
        return mov_rate;
    }

    public void setMov_rate(String mov_rate) {
        this.mov_rate = mov_rate;
    }

    public String getMov_language() { return mov_language; }

    public void setMov_language(String mov_language)
    {
        this.mov_language = mov_language;
    }


}
