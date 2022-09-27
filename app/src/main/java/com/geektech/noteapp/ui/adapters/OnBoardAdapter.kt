package com.geektech.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geektech.noteapp.ui.fragments.onboard.OnBoardFragment

class OnBoardAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return  3
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardFragment().apply {
            arguments = Bundle().apply {
                putInt("position",position)
            }
        }
    }
}