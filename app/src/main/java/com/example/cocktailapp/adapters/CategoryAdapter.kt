package com.example.cocktailapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.BuildConfig.DEBUG
import com.example.cocktailapp.R
import com.example.cocktailapp.activities.CategoryDetailActivity
import com.example.cocktailapp.models.CategoryModel

class CategoryAdapter(
    private val mContext: Context,
    private val categories: List<CategoryModel>,
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mCategory: CardView = view.findViewById(R.id.mCategory)
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
        holder.mCategory.setOnClickListener {
            val intent = Intent(mContext, CategoryDetailActivity::class.java)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}