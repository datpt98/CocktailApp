package com.example.cocktailapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.models.DrinkModel

class DrinkAdapter(
    private val drinks: List<DrinkModel>
) :
    RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var strDrink: TextView = view.findViewById(R.id.strDrink)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.drink_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: DrinkModel = drinks[position]
        viewHolder.strDrink.text = data.strDrink
    }

    override fun getItemCount() = drinks.size
}