package com.example.cocktailapp.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.cocktailapp.R
import com.example.cocktailapp.adapters.CategoryAdapter
import com.example.cocktailapp.adapters.DrinkAdapter
import com.example.cocktailapp.models.CategoryModel
import com.example.cocktailapp.models.DrinkModel
import com.example.cocktailapp.networks.API
import org.json.JSONException
import org.json.JSONObject

class CategoryDetailActivity : AppCompatActivity() {
    var drinkAdapter: DrinkAdapter? = null
    var drinkList: MutableList<DrinkModel> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        AndroidNetworking.initialize(getApplicationContext());

        var mCategory = intent.getStringExtra("mCategory")

        Log.d("The string is ", mCategory.toString())
        if (mCategory !== null) {
            getDrinks(mCategory.toString())
        }
    }

    private fun getDrinks(mCategory: String? = null) {
        var mRecyclerView: RecyclerView = findViewById(R.id.recyclerView)

        mRecyclerView.layoutManager = GridLayoutManager(this, 2)
        
        AndroidNetworking.get(API.Drinks)
            .addPathParameter("strCategory", mCategory)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val drinks = response.getJSONArray("drinks")

                        for (i in 0 until drinks.length()) {
                            val temp = drinks.getJSONObject(i)
                            val data = DrinkModel()
                            data.strDrink = temp.getString("strDrink")
                            data.strDrinkThumb = temp.getString("strDrinkThumb")
                            data.idDrink = temp.getString("idDrink")
                            drinkList.add(data)
                            drinkAdapter = DrinkAdapter(this@CategoryDetailActivity, drinkList)


                            mRecyclerView!!.adapter = drinkAdapter
                        }

                    } catch (e: JSONException) {

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }


}