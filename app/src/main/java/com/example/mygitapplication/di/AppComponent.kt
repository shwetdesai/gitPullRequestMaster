package com.example.mygitapplication.di

import android.app.Application
import com.example.mygitapplication.MyGitApplication
import com.example.mygitapplication.view.FirstFragment
import com.example.mygitapplication.view.MainActivity
import dagger.BindsInstance
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

    //Inject Main Fragment
    fun inject(fragment: FirstFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}