package com.sonia.github_mobile.ui.detailUser.following

import com.sonia.github_mobile.data.response.User


sealed class UserFollowingState {

    data class OnShowResultList(val listData :List<User>) : UserFollowingState()
    data class OnLoading(var isLoading : Boolean = false) : UserFollowingState()
    data class OnError(val error  : Throwable) : UserFollowingState()
    object OnEmpty :UserFollowingState()
}