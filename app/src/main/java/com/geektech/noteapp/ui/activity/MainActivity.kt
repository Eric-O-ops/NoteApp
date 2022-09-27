package com.geektech.noteapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.geektech.noteapp.R
import com.geektech.noteapp.data.locale.PreferenceHelper
import com.geektech.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private var sharedPreferences = PreferenceHelper()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (sharedPreferences.isOnBoardShowed()){
//            binding.navHostFragment.findNavController()
//                .navigate(
//                    R.id.action_onBoardViewPageFragment_to_noteAppMainFragment
//                )
//        }else{
//            binding.navHostFragment.findNavController()
//                .navigate(
//                    R.id.onBoardViewPageFragment
//                )
//        }
    }
}