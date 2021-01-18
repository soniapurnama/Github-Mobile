package com.sonia.github_mobile.ui.main

import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.ui.detailUser.follower.UserFollowerState


sealed class MainState {

    data class OnShowResultSearch(val listData :List<User>) : MainState()
    data class OnLoading(var isLoading : Boolean = false) : MainState()
    data class OnError(val error  : Throwable) : MainState()
}