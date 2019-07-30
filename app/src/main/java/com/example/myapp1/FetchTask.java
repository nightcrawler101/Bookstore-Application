package com.example.myapp1;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;


/**
 * FetchTask
 * This class handles querying the Google Books API, receiving the results, parsing them, building
 * an ArrayList of Book Objects, and returning the result
 */
class FetchTask extends AsyncTask<String, Void, ArrayList<Book>> {
    private final AsyncResponse delegate;
    private final String queryURI;
    private final int maxResults;

    // Constructor
    public FetchTask(AsyncResponse delegate, String queryURI, int maxResults) {
        this.delegate = delegate;
        this.queryURI = queryURI;
        this.maxResults = maxResults;
    }

    // Interface to delegate the onPostExecute actions
    public interface AsyncResponse {
        void processFinish(ArrayList<Book> output);
    }

    /**
     * doInBackground
     *
     * @param params optional parameters
     * @return an ArrayList of Book Objects
     */
    @Override
    protected ArrayList<Book> doInBackground(String... params) {
        // Create a new API Object
        API mApi = new API();
        // Call the API and get the results in a String variable
        String jsonResults = mApi.callAPI(queryURI, maxResults);
        // If the results are not null proceed to parsing and creating Book Objects
        if (jsonResults != null) {
            try {
                // Convert the results from String to JSONObject
                JSONObject jsonObject = new JSONObject(jsonResults);
                // Get the items node (Books) from the JSONObject
                JSONArray resultsArray = jsonObject.getJSONArray("items");
                // Count the results
                int countResults = resultsArray.length();
                // Create an ArrayList to hold the parsed collection of results
                ArrayList<Book> parsedResults = new ArrayList<>();
                // Loop through the resultsArray to parse the Books out
                for (int i = 0; i < countResults; i++) {
                    // Get the Book record
                    JSONObject bookRecord = resultsArray.getJSONObject(i);
                    // Get the volume info node from the Book record
                    JSONObject bookVolumeInfo = bookRecord.getJSONObject("volumeInfo");
                    JSONObject price=bookRecord.getJSONObject("saleInfo");
                    // Get the title from the volume info
                    String bookTitle = bookVolumeInfo.getString("title");
                    //------------------------------------------------------------------------------
                    // AUTHORS
                    //------------------------------------------------------------------------------
                    // Some books don't have an authors node, use try/catch to prevent null pointers

                    JSONObject bookAuthors1;
                    int bookAuthors2=0;

                    JSONArray bookAuthors;

                    try {
                        bookAuthors1 =price.getJSONObject("listPrice");
                        bookAuthors2=bookAuthors1.getInt("amount");

                    } catch (JSONException ignored) {
                    }
                    // Convert the authors to a string
                    String bookAuthorsString = "";
                    // If the author is empty, set it as "Unknown"
                    if (bookAuthors2==0) {
                        bookAuthorsString = "Price not available";
                    } else {
                        // Format the authors as "author1, author2, and author3"
                        bookAuthorsString=String.valueOf(bookAuthors2);

                       /* int countAuthors = bookAuthors1.length();
                        for (int e = 0; e < countAuthors; e++) {
                            String author = bookAuthors.toString(e);
                            if (bookAuthorsString.isEmpty()) {
                                bookAuthorsString = author;
                            } else if (e == countAuthors - 1) {
                                bookAuthorsString = bookAuthorsString + " and " + author;
                            } else {
                                bookAuthorsString = bookAuthorsString + ", " + author;
                            }
                        }*/
                    }
                    //------------------------------------------------------------------------------
                    // IMAGE LINKS
                    //------------------------------------------------------------------------------
                    JSONObject bookImageLinks = null;
                    try {
                        bookImageLinks = bookVolumeInfo.getJSONObject("imageLinks");
                    } catch (JSONException ignored) {
                    }
                    // Convert the image link to a string
                    String bookSmallThumbnail = "";
                    if ( bookImageLinks == null){
                        bookSmallThumbnail = "null";
                    }else{
                        bookSmallThumbnail  = bookImageLinks.getString("smallThumbnail");
                    }
                    // Create a Book object
                    Book mBook = new Book(bookTitle, bookAuthorsString, bookSmallThumbnail);
                    // Add it to the array
                    parsedResults.add(i, mBook);
                }
                // Return the results
                return parsedResults;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * onPostExecute
     * Once the background operation is completed, pass the results through the delegate method
     *
     * @param parsedResults an ArrayList of Book Objects
     */
    @Override
    protected void onPostExecute(ArrayList<Book> parsedResults) {
        delegate.processFinish(parsedResults);
    }
}



