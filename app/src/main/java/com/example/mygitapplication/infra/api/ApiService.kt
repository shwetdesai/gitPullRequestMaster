package com.example.mygitapplication.infra.api

import com.example.mygitapplication.infra.model.Constants.Companion.GIT_PULL_URL
import com.example.mygitapplication.infra.model.GitData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GIT_PULL_URL)
    suspend fun getPullRequestData(
        @Query("state") state:String
    ): ArrayList<GitData.GitPullRequest>
}