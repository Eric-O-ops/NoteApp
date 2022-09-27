package com.geektech.noteapp.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.geektech.noteapp.R
import com.geektech.noteapp.data.locale.PreferenceHelper
import com.geektech.noteapp.databinding.ActivityMainBinding
import com.geektech.noteapp.ui.fragments.onboard.OnBoardMainFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var pef:SharedPreferences

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        pef = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
//
//        val editor = pef.edit()
//        editor.putBoolean("key",true)
//        editor.apply()
//
//       if (pef.getBoolean("key",false)){
//           findNavController(6).navigate(
//               OnBoardMainFragmentDirections.actionOnBoardViewPageFragmentToNoteAppMainFragment()
//           )
//
//       }
    }
}