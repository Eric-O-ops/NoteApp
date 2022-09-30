package com.geektech.noteapp.ui.fragments.onboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.App
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentOnBoardMainBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding:FragmentOnBoardMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeViewPageRes()
        goToMainScreenOfApp()

    }

    private fun changeViewPageRes(){

        when(requireArguments().getInt("position")){
            0 ->{
                binding.animationView.setAnimation("anim_picture_screen_one.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_one)

            }
            1 ->{
                binding.animationView.setAnimation("anim_picture_screen_two.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_two)

            }
            2 ->{
                binding.animationView.setAnimation("anim_picture_screen_three.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_three)

            }
        }
    }

    @SuppressLint("ResourceType")
    private fun goToMainScreenOfApp(){
        val screenPosition = requireArguments().getInt("position")

        if (screenPosition == 2){
            binding.textSkip.visibility = View.GONE
            binding.startWork.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                   goToMainFragment()
                    onBoardWasActivated()
                }
            }
        } else{
            binding.startWork.visibility = View.GONE
            binding.textSkip.apply {
                visibility = View.VISIBLE
                setOnClickListener {

                    goToMainFragment()
                    onBoardWasActivated()
                }
            }
        }
    }

    private fun goToMainFragment(){
        findNavController().navigate(
            OnBoardMainFragmentDirections.actionOnBoardViewPageFragmentToNoteAppMainFragment()
        )
    }

    private fun onBoardWasActivated(){
        App.preferenceHelper.putValue(true)
   }
}