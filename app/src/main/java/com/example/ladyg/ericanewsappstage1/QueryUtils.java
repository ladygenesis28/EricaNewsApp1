package com.example.ladyg.ericanewsappstage1;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

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

import static com.example.ladyg.ericanewsappstage1.MainActivity.LOG_TAG;
import static com.example.ladyg.ericanewsappstage1.MainActivity.fetchNewsData;

public class QueryUtils {


    public static List<News> fetchNewsData(String requestUrl) {

        Log.i(LOG_TAG, "Fetch article data");

        // Create URL object using our createUrl method
        //should I be adding this or no

        URL url = createUrl(JSON_URL_NEWS);

        // This method is converting our url String to a URL object

        URL url = createUrl(requestUrl);


        // Perform a HTTP request on the URL to receive a JSON response back

        String jsonResponse = null;

        try {

            // This is where we call our makeHttpRequest method

            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {

            // Here we catch any errors that might occur when making a Http request

            Log.e(LOG_TAG, "Problem making the HTTP request.", e);

        }

        // Here we call our extractResultsFromJson method to parse the response we get from
        // calling makeHttpRequest
        //already have this method way down below. Should it be up here???
        List<News> news = extractResponseFromJson(jsonResponse);

        // Then we return the list of News that we've parsed via extractResponseFromJson
        return news;

    }
    //Is it suppose to be extractResponseFromJson or no??

    private static News extractResultsFromJson(String jsonResponse) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            // TODO: Return null should be inside the if block just above, otherwise it will always return
            return null;

        }
        //Should I be removing this block: line 81-131??

        private String makeHttpRequest (URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream (InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
//should I be removing this block??
        private URL createUrl (String jsonUrlNews){
            URL url = null;
            try {
                url = new URL(jsonUrlNews);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        List<News> news = new ArrayList<>();
        {

            try {
                // Parse JSON results
                JSONObject baseJsonResponse = new JSONObject(jsonResponse);

                // TODO: This is close but there is a "response" JSONObject which comes before JSONArray "results"

                // Extract the JSONArray associated with the key called "results",
                // which represents a list of results (or news).
                JSONObject newsResponse = baseJsonResponse.getJSONObject("response");
                JSONArray newsResults = baseJsonResponse.getJSONArray("results");

                // TODO: Add the for loop to iterate through the newsResults JSONArray
                //Erica: what does loop mean

                // Get the title, section, date and author out of the currentNews Item inside =??

                // the for loop. Make sure the two lines of code below are included inside the

                // for loop at the bottom. Each time the loop iterates we are creating a new

                // News object and passing it the news article title, section, date and author.

                // We are then adding the News object to the news list.

                //Erica: I am not understanding this part. Is all this under the Todo list??

                News newsArticle = new News(title, section, date, author);

                news.add(newsArticle);

            } catch (JSONException e) {

                // Catch error exception
                Log.e("MainActivity", "Problem parsing the news JSON results", e);

            }

            // Return the list of news

            return news;
        }
    }
}





