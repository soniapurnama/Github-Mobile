package com.sonia.github_mobile.data.response


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<User>,
    @SerializedName("total_count")
    var totalCount: Int
)