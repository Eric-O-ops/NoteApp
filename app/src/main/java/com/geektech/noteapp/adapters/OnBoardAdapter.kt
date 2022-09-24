package com.geektech.noteapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.geektech.noteapp.fragments.onboard.OnBoardMainFragment

class OnBoardAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return  3
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardMainFragment().apply {
            arguments = Bundle().apply {
                putInt("position",position)
            }
        }
    }
}