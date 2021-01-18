package com.sonia.github_mobile.utils.view

import com.sonia.github_mobile.data.response.User


interface OnItemClicked {

    //fun onArticleClicked(article: ArticleEnitity){}
    fun onUserClicked(user:User){}
}