package com.example.mygitapplication

import android.app.Application
import com.example.mygitapplication.di.AppComponent
import com.example.mygitapplication.di.DaggerAppComponent
import dagger.Component

class MyGitApplication: Application() {

    private var appComponent: AppComponent? = null
    companion object{

        private var instance: MyGitApplication? = null

        private const val TAG = "MyGitApplication"

        fun getInstance(): MyGitApplication? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
        getInstance()?.getAppComponent()?.inject(this)
    }


    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    private fun setupDependencyInjection() {
        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}
