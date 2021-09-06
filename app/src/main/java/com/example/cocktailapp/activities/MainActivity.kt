package com.example.cocktailapp.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.example.cocktailapp.R
import com.androidnetworking.error.ANError

import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.cocktailapp.adapters.CategoryAdapter
import com.example.cocktailapp.models.CategoryModel
import com.example.cocktailapp.networks.API
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    var categoryAdapter: CategoryAdapter? = null
    var categoryList: MutableList<CategoryModel> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(getApplicationContext());

        var mRecycleView: RecyclerView = findViewById(R.id.recycleView)
        mRecycleView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        var mCurDate: TextView = findViewById(R.id.curDate)

        val currentDateTime = LocalDateTime.now()
        mCurDate.text = currentDateTime.format(DateTimeFormatter.ISO_DATE)

        getRandomCocktail()


        AndroidNetworking.get(API.Categories)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val categories = response.getJSONArray("drinks")
                        Log.d("The response is ", categories.toString())

                        for (i in 0 until categories.length()) {
                            val temp = categories.getJSONObject(i)
                            val data = CategoryModel()
                            data.strCategory = temp.getString("strCategory")
                            categoryList.add(data)
                            categoryAdapter = CategoryAdapter(this@MainActivity, categoryList)


                            mRecycleView!!.adapter = categoryAdapter
                        }

                    } catch (e: JSONException) {

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })


    }

    private fun getRandomCocktail() {
        AndroidNetworking.get(API.Random)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val drinks = response.getJSONArray("drinks")
                    val curData = drinks.getJSONObject(0)
                    var image: ImageView = findViewById(R.id.randomCocktail)
                    var randomName: TextView = findViewById(R.id.randomName)
                    var randomCategory: TextView = findViewById(R.id.randomCategory)

                    randomName.text = curData.getString("strDrink")
                    randomCategory.text = curData.getString("strCategory")
                    Picasso.get()
                        .load(curData.getString("strDrinkThumb"))
                        .into(image)
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }
            })
    }


}