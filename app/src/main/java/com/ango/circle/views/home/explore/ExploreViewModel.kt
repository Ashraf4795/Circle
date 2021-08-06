package com.ango.circle.views.home.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ango.circle.core.data.model.Circle
import com.ango.circle.core.interactors.home.IHomeInteractor
import java.text.SimpleDateFormat
import java.util.*

class ExploreViewModel(private val homeInteractor: IHomeInteractor) : ViewModel() {
    val DATE_TIME_DEFAULT_FORMATE="d MMM yyyy" // 10 JAN 2021
    private val _circlesMutableLiveData = MutableLiveData<Circle>()
    val circlesLiveData: LiveData<Circle> = _circlesMutableLiveData
    val filteredCirclesMutableLiveData:LiveData<Circle> = MutableLiveData()

    init{
        getCircles()
    }

    private fun getCircles() {}

    fun getDateAndTime():String{
        val currentDateTime = Calendar.getInstance().time
        val simpleDateFormatter = SimpleDateFormat.getDateTimeInstance().format(currentDateTime)
        return simpleDateFormatter.format(simpleDateFormatter)
    }

    fun handleSearchQuery(query:String) {}
}