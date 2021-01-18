package com.sonia.github_mobile.data.repo

import com.sonia.github_mobile.data.ApiService
import com.sonia.github_mobile.data.response.SearchUserResponse
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.data.response.UserDetail
import retrofit2.Response


class UserRepo (val service: ApiService){


    suspend fun getSearchUserResponse(query :String) :Response<SearchUserResponse>{
        return service.searchUser(query)
    }

    suspend fun getUserFollowing(username:String) :Response<List<User>>{
        return service.followingUser(username)
    }


    suspend fun getUserFollower(username:String) :Response<List<User>>{
        return  service.followerUser(username)
    }

    suspend fun getDetailUser(username: String) :Response<UserDetail>{
        return service.detailUser(username)
    }
}