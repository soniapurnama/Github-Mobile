package com.sonia.github_mobile.ui.detailUser.following

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sonia.github_mobile.data.repo.UserRepo

import com.sonia.github_mobile.utils.mvvm.StateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class UserFollowingViewModel(val repo: UserRepo) : StateViewModel<UserFollowingState>(){


    fun doLoadFollowing(username:String){
        state.postValue(UserFollowingState.OnLoading(true))
        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d(TAG,"do load following $username")

                val result = repo.getUserFollowing(username)
                val listUser = result.body()
                Log.d(TAG,"success load following  ${Gson().toJsonTree(result.body())}")

                state.postValue(UserFollowingState.OnLoading(false))

                if(result.isSuccessful){

                    state.postValue(listUser?.let { UserFollowingState.OnShowResultList(it) })

                }else{
                    Log.d(TAG,"failed load following list")
                }
            }catch (e: Exception){

                Log.d(TAG,"failed load following list: ${e.localizedMessage}")
                state.postValue(UserFollowingState.OnLoading(false))
                state.postValue(UserFollowingState.OnError(e))
            }
        }
    }

}