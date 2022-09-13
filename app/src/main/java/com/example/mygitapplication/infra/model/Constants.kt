package com.example.mygitapplication.infra.model

class Constants {

    companion object {
        const val CUSTOM_CONNECT_TIMEOUT = 40L // seconds

        const val CUSTOM_READ_TIMEOUT = 40L
//        https://api.github.com/users/shwetdesai/repos
        const val GIT_BASE_URL = "https://api.github.com/repos/shwetdesai/"
        const val GIT_PULL_URL = "gitPullRequestMaster/pulls"
    }
}