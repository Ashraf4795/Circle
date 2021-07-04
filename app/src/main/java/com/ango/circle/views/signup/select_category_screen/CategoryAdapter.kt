package com.ango.circle.views.signup.select_category_screen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.core.data.model.Category
import com.ango.circle.databinding.CategoryCardLayoutBinding
import com.bumptech.glide.Glide

class CategoryAdapter(private val categoryItems: MutableList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    inner class CategoryViewHolder(val categoryCard: CategoryCardLayoutBinding) :
        RecyclerView.ViewHolder(categoryCard.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryItems[position]

        with(holder) {
            categoryCard.categoryTextId.text = categoryItem.categoryName
            Glide.with(holder.itemView)
                .load(categoryItem.categoryImg)
                .into(holder.categoryCard.categoryIconId)
            addCategoryClickListener(this, categoryItem)
        }
    }

    private fun addCategoryClickListener(categoryCard: CategoryViewHolder, categoryItem: Category) {
        categoryCard.categoryCard.root.setOnClickListener {
            Log.d("category item", "category item clicked:$categoryItem")
            //TODO: add this category to temp list to add it to User interests list.
        }
    }

    fun addItem(category: Category) {
        categoryItems.add(category)
        notifyDataSetChanged()
    }

    override fun getItemCount() = categoryItems.size
}