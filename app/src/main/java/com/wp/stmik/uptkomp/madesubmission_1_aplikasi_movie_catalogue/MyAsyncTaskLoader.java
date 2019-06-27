package com.wp.stmik.uptkomp.madesubmission_1_aplikasi_movie_catalogue;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> mData;
    private boolean mHasResult = false;

    private String mTitleMovie;

    public MyAsyncTaskLoader(final Context context, String titleMovie) {
        super(context);

        onContentChanged();
        this.mTitleMovie = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    public void deliverResult(ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "397ba500280dd57816029481bdbbd000";

    private void onReleaseResources(ArrayList<MovieItems> data) {
    }

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItems> movieItems_ = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +API_KEY+"&language=en-US&query="+mTitleMovie;


        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItems_.add(movieItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItems_;
    }
}
