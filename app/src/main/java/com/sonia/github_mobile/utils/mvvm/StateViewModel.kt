package com.sonia.github_mobile.utils.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class StateViewModel<State> : ViewModel(){

    protected val TAG =this.javaClass.simpleName
    val state = MutableLiveData<State>()
}