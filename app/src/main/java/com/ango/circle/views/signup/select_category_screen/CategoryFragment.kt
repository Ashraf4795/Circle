package com.ango.circle.views.signup.select_category_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ango.circle.R
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.LoadingState
import com.ango.circle.core.state.SuccessState
import com.ango.circle.core.utils.showToast
import com.ango.circle.databinding.FragmentCategoryBinding
import com.ango.circle.views.signup.signup_screen.SignUpViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
private const val TAG = "CategoryFragment"

class CategoryFragment : Fragment() {

    private lateinit var categoryFragmentBinding:FragmentCategoryBinding
    private val signUpViewModel:SignUpViewModel by viewModel()
    private val categoryAdapter:CategoryAdapter = CategoryAdapter(mutableListOf())
    private val categoryFragmentJobs = mutableListOf<Job>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoryFragmentBinding = FragmentCategoryBinding.inflate(inflater,container,false)
        return categoryFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoryRecyclerView()
        initCategoryStateObservation()
        getCategories()

    }

    private fun initCategoryRecyclerView() {
        with(categoryFragmentBinding.categoryRvId) {
            layoutManager = GridLayoutManager(requireActivity(),2)

            adapter = categoryAdapter
        }
    }

    private fun getCategories() {
        categoryFragmentJobs += viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            signUpViewModel.getCategories()
        }
    }

    private fun initCategoryStateObservation() {
        signUpViewModel.categoriesState.observe(viewLifecycleOwner) { categoriesState ->
            when(categoriesState) {
                is SuccessState<*> -> {
                    //TODO: go to welcome user page
                    showToast(requireActivity(),this.resources.getString(R.string.successful_signup_msg), Toast.LENGTH_LONG)
                    val categoryList = categoriesState.data as List<*>
                    categoryList.forEach{
                        categoryAdapter.addItem(it as Category)
                    }
                }
                is LoadingState<*> ->{
                    showToast(requireActivity(),"Loading", Toast.LENGTH_LONG)
                }
                is ErrorState -> {
                    Logger.wtf(categoriesState.errorCode?: "errorCode is null",categoriesState)
                }
            }
        }
    }
}