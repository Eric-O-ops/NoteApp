package com.geektech.noteapp.fragments.onboard

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.text.RelativeDateTimeFormatter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.R
import com.geektech.noteapp.databinding.FragmentOnBoardMainBinding

class OnBoardMainFragment : Fragment() {
    private lateinit var binding:FragmentOnBoardMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       changeViewPageRes()
        goToMainScreenOfApp()

    }


    private fun changeViewPageRes(){
        val orangeColor = R.drawable.ic_dot_circle_orange
        val whiteColor = R.drawable.ic_dot_circle_white
        when(requireArguments().getInt("position")){
            0 ->{
                changeDotsColor(orangeColor,whiteColor,whiteColor)
                binding.animationView.setAnimation("anim_picture_screen_one.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_one)

            }
            1 ->{
                changeDotsColor(whiteColor,orangeColor,whiteColor)
                binding.animationView.setAnimation("anim_picture_screen_two.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_two)

            }
            2 ->{
                changeDotsColor(whiteColor,whiteColor,orangeColor)
                binding.animationView.setAnimation("anim_picture_screen_three.json")
                binding.textUnderAnimPicture.setText(R.string.text_under_anim_picture_three)

            }

        }

    }
    private fun changeDotsColor(dotOne:Int, dotTwo:Int, dotThree:Int){
        binding.dotOne.setImageResource(dotOne)
        binding.dotTwo.setImageResource(dotTwo)
        binding.dotThree.setImageResource(dotThree)

    }
    @SuppressLint("ResourceType")
    private fun goToMainScreenOfApp(){
        val screenPosition = requireArguments().getInt("position")
        if (screenPosition == 2){
            binding.textSkip.visibility = View.GONE
            binding.startWork.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    findNavController().navigate(
                        R.id.action_onBoardViewPageFragment_to_noteAppMainFragment
                    )
                }
            }


        } else{
            binding.startWork.visibility = View.GONE
            binding.textSkip.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    findNavController().navigate(
                        R.id.action_onBoardViewPageFragment_to_noteAppMainFragment
                    )

                }
            }
        }
    }

}