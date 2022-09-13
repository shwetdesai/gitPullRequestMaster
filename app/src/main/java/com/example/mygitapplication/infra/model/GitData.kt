package com.example.mygitapplication.infra.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitData {

    data class GitPullRequest(
        @SerializedName("title") @Expose var title: String? = null,
        @SerializedName("created_at") @Expose var createdAt: String? = null,
        @SerializedName("closed_at") @Expose var closedAt: String? = null,
    )

    data class User(
        @SerializedName("login") @Expose var userName: String? = null,
        @SerializedName("image") @Expose var userImage: String? = null,
    )
}