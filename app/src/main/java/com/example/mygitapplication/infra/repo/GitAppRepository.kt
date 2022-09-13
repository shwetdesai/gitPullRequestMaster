package com.example.mygitapplication.infra.repo

import com.example.mygitapplication.infra.api.ApiService
import com.example.mygitapplication.infra.model.GitData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object GitAppRepository {
    suspend fun getRepoInfo(apiService: ApiService,state: String) : Flow<ResultWrapper<ArrayList<GitData.GitPullRequest>>> {
        return flow{
            when (val data = safeApiCall { apiService.getPullRequestData(state) }) {
                is ResultWrapper.Error -> emit(
                    ResultWrapper.Error(
                        data.throwable,
                        data.errorMessage
                    )
                )
                is ResultWrapper.Success -> emit(
                    ResultWrapper.Success(
                        data.value
                    )
                )
            }
        }
    }
}