package com.example.cocktailapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import com.example.cocktailapp.activities.MainActivity
import com.example.cocktailapp.models.CategoryModel

class CategoryAdapter(private val categories: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var categoryTitle: TextView = view.findViewById(R.id.categoryTitle)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data: CategoryModel = categories[position]

        holder.categoryTitle.text = data.strCategory
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}