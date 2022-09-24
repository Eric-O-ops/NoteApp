package com.geektech.noteapp.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.noteapp.adapters.OnBoardAdapter
import com.geektech.noteapp.databinding.FragmentOnBoardViewPageBinding


class OnBoardViewPageFragment : Fragment() {

    private lateinit var binding:FragmentOnBoardViewPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardViewPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPage.adapter = OnBoardAdapter(this@OnBoardViewPageFragment)

    }

}