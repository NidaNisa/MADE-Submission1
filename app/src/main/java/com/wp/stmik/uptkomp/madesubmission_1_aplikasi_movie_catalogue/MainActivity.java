package com.wp.stmik.uptkomp.madesubmission_1_aplikasi_movie_catalogue;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    ListView listView;
    MovieAdapter adapter;
    ImageView imgPoster;
    Button btnCari;
    EditText editTitle;

    static final String EXTRAS_MOVIE = "EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView    = (ListView)findViewById(R.id.lvMovie);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l)
            {
                MovieItems item = (MovieItems) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);

                intent.putExtra(DetailMovieActivity.EXTRA_TITLE, item.getMov_title());
                intent.putExtra(DetailMovieActivity.EXTRA_POSTER_JPG,item.getMov_image());
                intent.putExtra(DetailMovieActivity.EXTRA_LANGUAGE,item.getMov_language());
                intent.putExtra(DetailMovieActivity.EXTRA_RATE_COUNT,item.getMov_rate_count());
                intent.putExtra(DetailMovieActivity.EXTRA_RATE,item.getMov_rate());
                intent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW,item.getMov_synopsis());
                intent.putExtra(DetailMovieActivity.EXTRA_RELEASE_DATE,item.getMov_date());

                startActivity(intent);
            }
        });

        editTitle  = (EditText)findViewById(R.id.edit_title);
        imgPoster   = (ImageView)findViewById(R.id.imgMoviePict);

        btnCari     = (Button)findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(movieListener);

        String title_movie = editTitle.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, title_movie);

        getLoaderManager().initLoader(0, bundle, this);
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int i, Bundle bundle) {
        String titleMovie = "";
        if (bundle != null){
            titleMovie = bundle.getString(EXTRAS_MOVIE);
        }

        return new MyAsyncTaskLoader(this,titleMovie);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> movieItems) {
        adapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title_movie = editTitle.getText().toString();
            if(TextUtils.isEmpty(title_movie)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, title_movie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
