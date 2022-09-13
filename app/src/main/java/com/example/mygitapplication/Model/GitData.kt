package com.example.mygitapplication.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitData {

    data class gitPullRequest(
        @SerializedName("title") @Expose var title: String? = null,
        @SerializedName("created_at") @Expose var createdAt: String? = null,
        @SerializedName("closed_at") @Expose var closedAt: String? = null,
    )


}