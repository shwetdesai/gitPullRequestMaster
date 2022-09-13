package com.example.mygitapplication.infra.api

import com.example.mygitapplication.infra.model.GitData
import retrofit2.http.GET

interface ApiService {

    @GET()
    suspend fun getPullRequestData(): GitData.GitPullRequest
}