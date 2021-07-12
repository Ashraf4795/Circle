package com.ango.circle.views.signup.signup_flow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ango.circle.views.signup.select_category_screen.CategoryFragment
import com.ango.circle.views.signup.select_gender_screen.SelectGenderFragment

class SignUpSelectFlowAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val listOfSignUpFragment = listOf(
        SelectGenderFragment(),
        CategoryFragment()
    )
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment = listOfSignUpFragment[position]
}