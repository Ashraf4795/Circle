package com.ango.circle.views.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.Circle
import com.ango.circle.views.home.explore.CategoriesAdapter
import com.ango.circle.views.home.explore.CirclesAdapter
import com.bumptech.glide.Glide


@BindingAdapter("circlesList")
fun setCirclesRecyclerViewAdapter(view: RecyclerView, list: List<Circle>?) {
    if (view.adapter == null) {
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        view.layoutManager = layoutManager
        list?.also {
            view.adapter = CirclesAdapter(it)
        }

    } else {
        list?.also {
            (view.adapter as CirclesAdapter).updateList(it)
        }
    }
}

@BindingAdapter("imageURL")
fun setImageViewWithGlide(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("categoriesList", "categoryClickAction")
fun setCategoriesRecyclerViewAdapter(
    view: RecyclerView,
    list: List<Category>?,
    action: (Category) -> Unit
) {
    if (view.adapter == null) {
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        view.layoutManager = layoutManager
        list?.also { view.adapter = CategoriesAdapter(it, action) }

    } else {
        list?.also { (view.adapter as CategoriesAdapter).updateList(it) }
    }
}

enum class CategoryIdKey {
    ALL,
    POPULAR,
    SPORTS,
    STUDY
}