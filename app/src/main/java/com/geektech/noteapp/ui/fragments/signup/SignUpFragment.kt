package com.geektech.noteapp.ui.fragments.signup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geektech.noteapp.App
import com.geektech.noteapp.databinding.FragmentSighnUpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import com.geektech.noteapp.R

class SignUpFragment : Fragment() {

    private lateinit var binding:FragmentSighnUpBinding
    private var auth: FirebaseAuth? = null
    private var storedVerificationId: String? = ""
    private lateinit var recentToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        binding = FragmentSighnUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()

    }

    private fun changeButtonText() = with(binding) {
        editTextCode.setOnClickListener {
            if (editTextCode.text.isNotEmpty()) {
                buttonConfirm.text = "Потвердить"
            }else{
                buttonConfirm.text = "Отпрвить"
            }
        }
    }

    private fun setUpListener() = with(binding) {
        buttonConfirm.setOnClickListener {
            if (editTextCode.text.isEmpty()) {
                checkNumber(editTextNumber.text.toString())
                Toast.makeText(requireContext(), "Code was sent", Toast.LENGTH_SHORT).show()
                buttonConfirm.text = "Потвердить"
            } else {
                verifyNumberWithCode(storedVerificationId, editTextCode.text.toString())
            }
        }
    }

    private fun verifyNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)

    }

    private fun checkNumber(phoneNumber: String) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(credential)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("TAG", "onVerificationFailed", e)

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                storedVerificationId = verificationId

            }
        }

        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(requireContext(), "Signuping was succeeded", Toast.LENGTH_SHORT).show()

                    App.preferenceHelper.putValue("signUp",true)
                    findNavController().navigate(R.id.action_signUpFragment_to_onBoardMainFragment)

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            requireContext(),
                            "Registration isn't successes",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }
}