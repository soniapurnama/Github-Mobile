package com.sonia.github_mobile.data

import com.sonia.github_mobile.data.response.SearchUserResponse
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.data.response.UserDetail
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 9534d37493b16b30b2623d7823d94eacc38d8e55")
    suspend fun searchUser(@Query("q")query :String) : Response<SearchUserResponse>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token 9534d37493b16b30b2623d7823d94eacc38d8e55")
    suspend fun followerUser( @Path("username")username :String) : Response<List<User>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token 9534d37493b16b30b2623d7823d94eacc38d8e55")
    suspend fun followingUser( @Path("username")username :String) : Response<List<User>>

    @GET("/users/{username}")
    @Headers("Authorization: token 9534d37493b16b30b2623d7823d94eacc38d8e55")
    suspend fun detailUser(@Path("username") username: String) : Response<UserDetail>

}