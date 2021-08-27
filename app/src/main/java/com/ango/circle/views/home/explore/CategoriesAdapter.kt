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
            getImage(item.categoryId ?: "", binding.categoryIconId)
            binding.categoryTextId.text = item.categoryName

            if (item.is_selected == true) {
                binding.categoryLayoutId.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.light_blue
                    )
                )
                binding.categoryTextId.setTextColor(binding.root.resources.getColor(R.color.white))
            }
        }

        private fun getImage(key: String, view: ImageView) {
            when (key) {
                CategoryIdKey.ALL.name -> view.setBackgroundResource(R.drawable.ic_layers)
                CategoryIdKey.POPULAR.name -> view.setBackgroundResource(R.drawable.ic_soccer_ball)
                CategoryIdKey.SPORTS.name -> view.setBackgroundResource(R.drawable.ic_running_stick_figure)
                CategoryIdKey.STUDY.name -> view.setBackgroundResource(R.drawable.ic_soccer_ball)
                else -> view.setBackgroundResource(R.drawable.ic_soccer_ball)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryCardLayoutBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.circleCategoryImageId.apply {
                setPaddingRelative(
                    20,
                    paddingTop,
                    paddingEnd,
                    paddingBottom
                )
            }
        }
        holder.bind(categoriesList[position], clickAction)
        holdersList.add(holder)
        holder.itemView.setOnClickListener {
            for (holderItem in holdersList) {
                holderItem.binding.categoryLayoutId.setBackgroundColor(
                    ContextCompat.getColor(
                        holder.binding.root.context,
                        R.color.white
                    )

                )
                holderItem.binding.categoryTextId.setTextColor(
                    holder.binding.root.resources.getColor(
                        R.color.black
                    )
                )
            }
            holder.binding.categoryTextId.setTextColor(holder.binding.root.resources.getColor(R.color.white))

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