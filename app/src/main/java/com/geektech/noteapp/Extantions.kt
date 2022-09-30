package com.geektech.noteapp

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.models.NoteModel

fun <T:Any> Fragment.setBackStackData(key: String, data: T, doBack: Boolean){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key,data)
    if (doBack) findNavController().navigateUp()

}
fun <T :Any> Fragment.getBackStackData(key: String,result: (T) -> (Unit)){
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
        ?.observe(viewLifecycleOwner){
            result(it)
        }
}