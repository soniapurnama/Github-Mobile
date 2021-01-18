package com.sonia.github_mobile.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sonia.github_mobile.data.repo.UserRepo
import com.sonia.github_mobile.utils.mvvm.StateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class MainViewModel(private val repo:UserRepo) :StateViewModel<MainState>(){

    fun doSearchUser(query:String){
        state.postValue(MainState.OnLoading(true))
        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d(TAG,"do search username ${query}")

                val result = repo.getSearchUserResponse(query)
                val listUser = result.body()?.items
                state.postValue(MainState.OnLoading(false))
                Log.d(TAG,"success search username ${Gson().toJsonTree(result.body())}")
                if(result.isSuccessful){

                    state.postValue(listUser?.let { MainState.OnShowResultSearch(it) })
                }else{
                    Log.d(TAG,"failed search username")
                }
            }catch (e:Exception){
                state.postValue(MainState.OnLoading(true))
                state.postValue(MainState.OnError(e))
                Log.d(TAG,"failed search username: ${e.localizedMessage}")
            }
        }
    }
}