package com.sonia.github_mobile.ui.detailUser

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sonia.github_mobile.data.repo.UserRepo
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.data.response.UserDetail
import com.sonia.github_mobile.utils.mvvm.StateViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class DetailUserViewModel(val repo: UserRepo) : StateViewModel<DetailUserState>(){

    fun doLoadDetailUser(username:String){

        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d(TAG,"do load user detail $username")

                val result = repo.getDetailUser(username)
                val userDetail = result.body()
                Log.d(TAG,"success load user detail  ${Gson().toJsonTree(result.body())}")
                if(result.isSuccessful){

                    state.postValue(userDetail?.let { DetailUserState.OnShowResultDetail(it) })
                }else{
                    Log.d(TAG,"failed load user detail ")
                }
            }catch (e: Exception){
                Log.d(TAG,"failed load user detail : ${e.localizedMessage}")
                state.postValue(DetailUserState.OnError(e))
            }
        }
    }
}

   