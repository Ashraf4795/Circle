package com.ango.circle.views.home.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.data.model.Circle
import com.ango.circle.core.repos.category.ICategoryRepository
import com.ango.circle.core.repos.circle.ICircleRepository
import com.ango.circle.core.state.LoadingState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.ango.circle.views.SearchViewModel
import com.ango.circle.views.home.CategoryIdKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExploreViewModel(
    private val circleRepository: ICircleRepository,
    private val categoryRepository: ICategoryRepository,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val selectedCategoryLiveData = MutableLiveData<String>()
    private val _circlesLiveData = MutableLiveData<List<Circle>>()
    private var circlesList: List<Circle>? = null
    private val _categoriesLiveData = MutableLiveData<List<Category>>()
    val circlesLiveData: LiveData<List<Circle>>
        get() = _circlesLiveData
    val categoriesLiveData: LiveData<List<Category>>
        get() = _categoriesLiveData

    fun getData() {
        viewModelScope.launch(IO) {
            categoryRepository.getCategoriesList { getCategories(it) }
            circleRepository.getCirclesList { getCirclesData(it) }
        }
    }

    private val getCirclesData: (State) -> Unit = {

        when (it) {
            is SuccessState<*> -> circlesList = it.data as List<Circle>
            is LoadingState<*> -> Log.i("explore", "loading")
            else -> Log.i("explore", "error")
        }
        circlesList?.also {
            _circlesLiveData.postValue(it)
        }
    }

    private val getCategories: (State) -> Unit = {

        when (it) {
            is SuccessState<*> -> _categoriesLiveData.postValue(it.data as List<Category>)
            is LoadingState<*> -> Log.i("explore", "loading")
            else -> Log.i("explore", "error")
        }
    }

    val getCirclesOfCategory: (Category) -> Unit = { category ->
        selectedCategoryLiveData.postValue(category.categoryId ?: "ALL")
        if (category.categoryId == CategoryIdKey.ALL.name) {
            circlesList?.also {
                _circlesLiveData.postValue(it)
            }
        } else {
            _circlesLiveData.postValue(circlesList?.filter { it.categoryId == category.categoryId })
        }
    }


    val searchViewModel = SearchViewModel { query ->
        viewModelScope.launch(IO) {
            if (query.isNullOrEmpty() || query.isNullOrBlank()) {
                circleRepository.getCirclesList { getCirclesData(it) }
            } else {
                circleRepository.getCirclesListByName(
                    query, getCirclesByNameCompleteListiner
                )
            }
        }
    }

    private val getCirclesByNameCompleteListiner: (State) -> Unit = {

        when (it) {
            is SuccessState<*> -> _circlesLiveData.postValue(it.data as List<Circle>)
            is LoadingState<*> -> Log.i("explore", "loading")
            else -> Log.i("explore", "error")
        }
    }


    fun getDate(): String {
        val format = SimpleDateFormat("dd MMM YYYY")
        val date = Calendar.getInstance().time
        return "Today ${format.format(date)}"
    }

    fun getName(): String {
        return "Hello, Osama"
    }
}
