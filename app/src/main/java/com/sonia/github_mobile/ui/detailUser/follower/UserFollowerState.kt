package com.sonia.github_mobile.ui.detailUser.follower

import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.ui.main.MainState


sealed class UserFollowerState {

    data class OnShowResultList(val listData :List<User>) : UserFollowerState()
    data class OnLoading(var isLoading : Boolean = false) : UserFollowerState()
    data class OnError(val error  : Throwable) : UserFollowerState()
    object OnEmpty :UserFollowerState()
}