package com.example.mygitapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.mygitapplication.MyGitApplication
import com.example.mygitapplication.R
import com.example.mygitapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        MyGitApplication.getInstance()?.appComponents?.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        Thread(Runnable {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                moveToMainFragment()
            }, 2500)
        }).start()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    // Function to move to the Main Fragment
    fun moveToMainFragment(){
        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
}