package com.geektech.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.noteapp.ui.adapters.OnBoardAdapter
import com.geektech.noteapp.databinding.FragmentOnBoardViewPageBinding


class OnBoardMainFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardViewPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnBoardViewPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = OnBoardAdapter(this@OnBoardMainFragment)
        binding.dotsIndicator.attachTo(binding.viewPager)

    }
}