package com.ango.circle.views.signup.select_category_screen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.R
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.utils.flip
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

            updateCategoryCardBorder(categoryItem.is_selected ?: false,categoryCard.categoryLayoutId)
            addCategoryClickListener(this, categoryItem)


        }
    }

    private fun updateCategoryCardBorder(selected: Boolean, categoryLayoutId: ConstraintLayout) {
        if(selected) {
            categoryLayoutId.setBackgroundResource(R.drawable.border_background)
        }else {
            categoryLayoutId.setBackgroundResource(0)
        }
    }

    private fun addCategoryClickListener(holder: CategoryViewHolder, categoryItem: Category) {
        holder.categoryCard.root.setOnClickListener {
            val isSelected = categoryItem.is_selected?.flip() ?: false
            categoryItem.is_selected = isSelected
            updateCategoryCardBorder(isSelected,holder.categoryCard.categoryLayoutId)
            println(categoryItems.toString())
        }
    }

    fun addItem(category: Category) {
        categoryItems.add(category)
        notifyDataSetChanged()
    }

    fun getCategoryItems(): MutableList<Category> {
        return categoryItems
    }

    override fun getItemCount() = categoryItems.size
}