package com.example.mygitapplication.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mygitapplication.infra.api.ApiService
import com.example.mygitapplication.infra.model.GitData
import com.example.mygitapplication.infra.network.ConnectionDetector
import com.example.mygitapplication.infra.repo.GitAppRepository
import com.example.mygitapplication.infra.repo.ResultWrapper
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainFragmentViewModel @javax.inject.Inject constructor(
    application: Application,val apiService: ApiService
) : AndroidViewModel(application) {

    var data = MutableLiveData<ArrayList<GitData.GitPullRequest>>()
    var image = MutableLiveData<String>()

    companion object{
        private const val TAG = "MainFragmentViewModel"
    }

    fun checkNetwork(): Boolean {
        var isValid = true
        if (!ConnectionDetector.isConnectingToInternet(getApplication())) {
            isValid = false
        }
        return isValid
    }


    fun getAllRepositoryFromGit(state: String){
        if(checkNetwork()){
            viewModelScope.launch {
                GitAppRepository.getRepoInfo(apiService,state).collect {
                    when (it) {
                        is ResultWrapper.Success -> onSuccessReposData(it.value)
                        is ResultWrapper.Error -> onFailureReposData(it.throwable)
                    }
                }
            }
        }
    }

    fun onSuccessReposData(list: ArrayList<GitData.GitPullRequest>){
        Log.i(TAG,"onSuccessReposData ${Gson().toJson(list)}")
        if(list.size > 0){
            val img = list[0].user?.userImage
            Log.i(TAG,"Image $img")
            image.postValue(img!!)
        }
        data.postValue(list)
    }

    fun onFailureReposData(throwable: Throwable?) {
        Log.i(TAG, "onFailureReposData $throwable")
    }


}