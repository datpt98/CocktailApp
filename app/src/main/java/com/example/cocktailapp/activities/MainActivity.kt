package com.example.cocktailapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
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
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var categoryAdapter: CategoryAdapter? = null
    var categoryList: MutableList<CategoryModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(getApplicationContext());

        getCategories()
    }

    private fun getCategories() {
        AndroidNetworking.get(API.Categories)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val categories = response.getJSONArray("drinks")

                        for (i in 0 until categories.length()) {
                            val temp = categories.getJSONObject(i)
                            val data = CategoryModel()
                            data.strCategory = temp.getString("strCategory")
                            categoryList.add(data)
                            showCategories()
                        }

                    } catch (e: JSONException) {

                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }

    private fun showCategories() {
        //categoryAdapter = CategoryAdapter(this@MainActivity, categoryList, this)
    }


}