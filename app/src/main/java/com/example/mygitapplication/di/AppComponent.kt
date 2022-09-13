package com.example.mygitapplication.di

import com.example.mygitapplication.MyGitApplication
import com.example.mygitapplication.view.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class,ViewModelModule::class])
interface AppComponent {

    // Inject Application
    fun inject(application: MyGitApplication?)

    //Inject Activity
    fun inject(activity: MainActivity)
}