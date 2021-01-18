package com.sonia.github_mobile.di

import android.content.Context
import com.google.gson.Gson
import com.sonia.github_mobile.data.ApiService
import com.sonia.github_mobile.data.repo.UserRepo
import com.sonia.github_mobile.ui.detailUser.DetailUserViewModel
import com.sonia.github_mobile.ui.detailUser.follower.UserFollowerViewModel
import com.sonia.github_mobile.ui.detailUser.following.UserFollowingViewModel
import com.sonia.github_mobile.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    //create okhttp
    //create retrofit
    single { createOkHttpClient(androidContext()) }
    single { createRetrofit(get()) }
    single { createApiService(get()) }


    //repo
    factory { UserRepo(get()) }


    //viewmodel
    viewModel { MainViewModel(get()) }
    viewModel { DetailUserViewModel(get()) }
    viewModel { UserFollowerViewModel(get()) }
    viewModel { UserFollowingViewModel(get()) }
}

fun createRetrofit(okHttpClient: OkHttpClient) :Retrofit{

    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(okHttpClient)
        .build()

}

fun createOkHttpClient(context : Context) :OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)