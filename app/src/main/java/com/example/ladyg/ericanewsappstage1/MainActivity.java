package com.example.ladyg.ericanewsappstage1;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String JSON_URL_NEWS = "http://content.guardianapis.com/search?format=json&show-tags=contributor&api-key=b3ee86e0-e862-44ff-81e5-421bd5983e37";

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final int NEWS_LOADER_ID = 1;


    /**
     * Adapter for the list of News
     */
    private RecyclerViewAdapter mAdapter;

    private RecyclerView recyclerView;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEmptyStateTextView = (TextView) findViewById(R.id.recyclerview_id);
        RecyclerView.setEmptyView(mEmptyStateTextView);


        // Create a new adapter that takes an empty list of news as input
        mAdapter = new RecyclerViewAdapter(this, new ArrayList<News>());

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager getLoaderManager = getLoaderManager();

        // Initialize the loader
        getLoaderManager.initLoader(NEWS_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new NewsLoader(this, JSON_URL_NEWS);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        // Set empty state text to display "No news found."
        mEmptyStateTextView.setText(R.string.no_news);

        mAdapter.clear();
        mAdapter = new RecyclerViewAdapter(MainActivity.this, data);
        if (data != null && !data.isEmpty()) {
            recyclerView.setAdapter(mAdapter);

        }

        @Override
        public void onLoaderReset(Loader < List < News >> loader) {
            // Loader reset, so we can clear out our existing data.
            mAdapter.clear();
        }
    }
}

