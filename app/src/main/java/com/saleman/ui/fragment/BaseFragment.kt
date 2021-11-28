package com.saleman.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.saleman.ui.MainActivity

open class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    fun showFilter(title: String) {
        mainActivity.rlSearch.visibility = View.VISIBLE
        mainActivity.rlFilter.visibility = View.VISIBLE

    }

    fun hideFilter() {
        mainActivity.rlSearch.visibility = View.GONE
        mainActivity.rlFilter.visibility = View.GONE
    }

    fun setTitle(title: String) {
        mainActivity.txtTitle.text = title
    }

    fun hideMenu() {
        mainActivity.imgMenu.visibility = View.GONE
    }


    fun hideBack() {
        mainActivity.imgBack.visibility = View.GONE

    }

    fun showMenu() {
        mainActivity.imgMenu.visibility = View.VISIBLE

    }

    fun showBack() {
        mainActivity.imgBack.visibility = View.VISIBLE
    }


    fun showButtom() {
        mainActivity.llBottom.visibility = View.VISIBLE
    }

    fun hideButtomBar() {
        mainActivity.llBottom.visibility = View.GONE
    }



}