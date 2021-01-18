package com.sonia.github_mobile.ui.detailUser.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter (fragmentManager: FragmentManager) :FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()


    fun addFragment(fragment: Fragment, title: String = "") {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    fun clearFragments() {
        fragmentList.clear()
        titleList.clear()
    }

    override fun getPageTitle(position: Int) = titleList[position]
    override fun getItem(position: Int) = fragmentList[position]
    override fun getCount(): Int = fragmentList.size

}