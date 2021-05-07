package com.edtechgrademy.com.grademy.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HomeVMFactory (private val arg : String = "sample") : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(arg)as T
        }
        throw IllegalArgumentException("View Model Not Found")
    }
}