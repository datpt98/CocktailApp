package com.example.cocktailapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.activities.MainActivity
import com.example.cocktailapp.models.CategoryModel

class CategoryAdapter(private val categories: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {
        var categoryTitle: TextView = view.findViewById(R.id.categoryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitle.text = categories[position]
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}