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

    private static final String JSON_URL_NEWS = "http://content.guardianapis.com/search?format=json&api-key=b3ee86e0-e862-44ff-81e5-421bd5983e37";

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

    List<News> news = new ArrayList<>();
    private News newsList;
    private AbstractList<News> news_webpublicationdate;
    private AbstractList<News> news_type;
    private AbstractList<News> news_sectionname;
    private AbstractList<News> news_sectionid;
    private AbstractList<News> news_webtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager getLoaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        getLoaderManager.initLoader(NEWS_LOADER_ID, null, this);

        // Kick off an {@link AsyncTask} to perform the network request
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute();


        recyclerView = findViewById(R.id.recyclertview_id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        // Create a new adapter that takes an empty list of news as input
        mAdapter = new RecyclerViewAdapter(this, new ArrayList<News>());

    }

    public static List<News> fetchNewsData() {
        return null;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new NewsLoader(this, JSON_URL_NEWS);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        // Clear the adapter of previous earthquake data
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    private News extractResultsFromJson(String jsonResponse) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
            }

            // Try to parse the JSON response string. If there's a problem with the way the JSON
            // is formatted, a JSONException exception object will be thrown.
            // Catch the exception so the app doesn't crash, and print the error message to the logs.
            try {

                // Create a JSONObject from the JSON response string
                JSONObject baseJsonResponse = new JSONObject(jsonResponse);

                // Extract the JSONArray associated with the key called "results",
                // which represents a list of results (or news).
                JSONArray newsResults = baseJsonResponse.getJSONArray("results");

                // For each news in the newsArray, create an {@link News} object
                for (int i = 0; i < newsResults.length(); i++) {

                    // Get a single new at position i within the list of news
                    JSONObject currentNews = newsResults.getJSONObject(i);

                    // For a given news, extract the JSONObject associated with the
                    // key called "properties", which represents a list of all properties
                    // for that new.
                    JSONObject response = currentNews.getJSONObject("response");

                    // Extract the value for the key called "type"
                    String type = response.getString("type");

                    // Extract the value for the key called " section id"
                    String sectionId = response.getString("sectionId");

                    // Extract the value for the key called "section name"
                    String sectionName = response.getString("sectionName");

                    // Extract the value for the key called " Web Publication Date"
                    String webPublicationDate = response.getString("Web Publication Date");

                    // Extract the value for the key called " Web Publication Date"
                    String webTitle = response.getString("WebTitle");

                    // Create a new {@link News} object with the title, type, section id, and date
                    // from the JSON response.
                    News news1 = new News(type, sectionId, sectionName, webPublicationDate, webTitle);

                    List<News> newsList = new ArrayList<>();

                    news_webtitle.add((News) newsList);
                    news_sectionname.add((News) newsList);
                    news_type.add((News) newsList);
                    news_sectionid.add((News) newsList);
                    news_webpublicationdate.add((News) newsList);

                }

            } catch (JSONException e) {
                // If an error is thrown when executing any of the above statements in the "try" block,
                // catch the exception here, so the app doesn't crash. Print a log message
                // with the message from the exception.
                Log.e("MainActivity", "Problem parsing the news JSON results", e);
            }

            // Return the list of news
            return (News) news;
        }
    }

