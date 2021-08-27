package com.ango.circle.views.home.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.R
import com.ango.circle.core.data.model.Category
import com.ango.circle.databinding.CategoryCardLayoutBinding
import com.ango.circle.views.home.CategoryIdKey

class CategoriesAdapter(
    private var categoriesList: List<Category>,
    private val clickAction: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var holdersList = ArrayList<CategoriesViewHolder>()

    class CategoriesViewHolder(val binding: CategoryCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category, action: (Category) -> Unit) {
            getImage(item.categoryImg ?: "", binding.categoryIconId)
            binding.categoryTextId.text = item.categoryName

            if (item.is_selected == true) {
                binding.categoryLayoutId.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.light_blue
                    )
                )
            }
        }

        private fun getImage(key: String, view: ImageView) {
            when (key) {
                CategoryIdKey.ALL.name -> view.setBackgroundResource(R.drawable.chat_notification_counter_icon)
                CategoryIdKey.POPULAR.name -> view.setBackgroundResource(R.drawable.logo)
                CategoryIdKey.SPORTS.name -> view.setBackgroundResource(R.drawable.border_background)
                CategoryIdKey.STUDY.name -> view.setBackgroundResource(R.drawable.logo)
                else -> view.setBackgroundResource(R.drawable.chat_notification_counter_icon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryCardLayoutBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesList[position], clickAction)
        holdersList.add(holder)
        holder.itemView.setOnClickListener {
            for (holder in holdersList) {
                holder.binding.categoryLayoutId.setBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.white
                    )
                )
            }
            holder.binding.categoryLayoutId.setBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.light_blue
                )
            )
            clickAction(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun updateList(list: List<Category>) {
        categoriesList = list
        notifyDataSetChanged()
    }
}