package com.example.cocktailapp.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktailapp.R

class DrinkActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        var mCategory = intent.getStringExtra("mCategory")
        if (mCategory !== null) {

        }
    }

    private fun getDrinks() {

    }


}