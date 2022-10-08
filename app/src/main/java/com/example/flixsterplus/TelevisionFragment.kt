package com.example.flixsterplus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
class TelevisionFragment : Fragment(),TelevisionOnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_television_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress_tv) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.TVList) as RecyclerView
        val context = view.context
        //recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        updateAdapter(progressBar, recyclerView)
        return view
    }
    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        var client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        // Using the client, perform the HTTP request
        client["https://api.themoviedb.org/3/tv/popular",
                params,
                object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                        val tvRawJSON : String = resultsJSON.toString()
                        val gson = Gson()

                        val arrayTelevisionType = object : TypeToken<List<Television>>() {}.type

                        val models : List<Television> = gson.fromJson(tvRawJSON,arrayTelevisionType)
                        recyclerView.adapter = TelevisionRecyclerViewAdapter(models, this@TelevisionFragment)

                        // Look for this in Logcat:
                        Log.d("TelevisionFragment", "response successful")
                        Log.d("TelevisionFragment",tvRawJSON)
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("TelevisionFragment", errorResponse)
                        }
                    }
                }]
    }

    override fun onItemClick(item: Television) {
        Toast.makeText(context, item.name, Toast.LENGTH_LONG).show()

        val intent = Intent(context,DetailActivity::class.java)
        //intent.putExtra(tv_extra,televisionSeries)
        context?.startActivity(intent)

    }
}