package com.naveeniiit119.news

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: newsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView.layoutManager=LinearLayoutManager(this)
        fetch()
        mAdapter= newsAdapter(this)
        recycleView.adapter=mAdapter

    }
    fun fetch()
    {
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
               val jsonObjectArray=it.getJSONArray("articles")
                val newsArray=ArrayList<items>()
                for(i in 0 until jsonObjectArray.length())
                {  val newsJsonObject=jsonObjectArray.getJSONObject(i)
                   val item=items(
                       newsJsonObject.getString("author"),
                       newsJsonObject.getString("title"),
                       newsJsonObject.getString("url"),
                       newsJsonObject.getString("urlToImage")

                   )
                    newsArray.add(item)

                }
                mAdapter.updateNews(newsArray)
            },
            { error ->
                // TODO: Handle error
            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(items: items) {
        val url = items.url
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
        
    }


}