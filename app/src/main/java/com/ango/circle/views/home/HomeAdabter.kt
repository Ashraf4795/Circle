package com.ango.circle.views.home

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.Circle
import com.ango.circle.views.home.explore.CategoriesAdapter
import com.ango.circle.views.home.explore.CirclesAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText


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

@BindingAdapter("textChangeCallBack")
fun setOnchangeListener(view: TextInputEditText, callback: (String) -> Unit) {
    view.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable) {
            callback(s.toString())
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
        }
    })
}

enum class CategoryIdKey {
    ALL,
    POPULAR,
    SPORTS,
    STUDY
}