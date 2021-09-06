package com.example.cocktailapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.activities.DrinkActivity
import com.example.cocktailapp.models.DrinkModel
import com.squareup.picasso.Picasso

class DrinkAdapter(
    private val mContext: Context,
    private val drinks: List<DrinkModel>
) :
    RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mDrink: CardView = view.findViewById(R.id.mDrink)
        var strDrink: TextView = view.findViewById(R.id.strDrink)
        var strDrinkThumb: ImageView = view.findViewById(R.id.strDrinkThumb)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_drink, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: DrinkModel = drinks[position]

        Log.d("The data is ", data.strDrinkThumb.toString())

        viewHolder.strDrink.text = data.strDrink
        Picasso.get()
            .load(data.strDrinkThumb)
            .into(viewHolder.strDrinkThumb)

        viewHolder.mDrink.setOnClickListener {
            val intent = Intent(mContext, DrinkActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount() = drinks.size
}