package com.geektech.noteapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.data.locale.PreferenceHelper
import com.geektech.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFirstScreen()
    }

    private fun showFirstScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (App.preferenceHelper.isOnBoardShowed()) {
            navGraph.setStartDestination(R.id.noteAppMainFragment)
        } else {
            navGraph.setStartDestination(R.id.onBoardMainFragment)
        }

        navController.graph = navGraph
    }
}