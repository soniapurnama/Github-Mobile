package com.sonia.github_mobile.ui.detailUser.follower

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sonia.github_mobile.data.repo.UserRepo
import com.sonia.github_mobile.ui.main.MainState
import com.sonia.github_mobile.utils.mvvm.StateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class UserFollowerViewModel(val repo: UserRepo) :StateViewModel<UserFollowerState>(){


    fun doLoadFollower(username:String){
        state.postValue(UserFollowerState.OnLoading(true))
        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d(TAG,"do load follower $username")

                val result = repo.getUserFollower(username)
                val listUser = result.body()
                Log.d(TAG,"success load follower  ${Gson().toJsonTree(result.body())}")

                state.postValue(UserFollowerState.OnLoading(false))

                if(result.isSuccessful){

                    state.postValue(listUser?.let { UserFollowerState.OnShowResultList(it) })

                }else{
                    Log.d(TAG,"failed load follower list")
                }
            }catch (e: Exception){

                Log.d(TAG,"failed load follower list: ${e.localizedMessage}")
                state.postValue(UserFollowerState.OnLoading(false))
                state.postValue(UserFollowerState.OnError(e))
            }
        }
    }

}