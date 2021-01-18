package com.sonia.github_mobile.ui.detailUser

import com.sonia.github_mobile.data.response.UserDetail


sealed class DetailUserState {
    data class OnShowResultDetail(var userDetail: UserDetail) : DetailUserState()
    data class OnLoading(var isLoading : Boolean = false) : DetailUserState()
    data class OnError(val error  : Throwable) : DetailUserState()
}
