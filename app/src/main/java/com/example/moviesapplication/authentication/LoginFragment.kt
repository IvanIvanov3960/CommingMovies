package com.example.moviesapplication.authentication

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.moviesapplication.MainActivity
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.Observer

class LoginFragment : Fragment(){

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private lateinit var binding: LoginFragmentBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        binding = LoginFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.loginButton.setOnClickListener {
            if (binding.emailInput.text.isEmpty()) {
                Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show()
            }
            if (binding.passwordInput.text.isEmpty()){
                Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show()
            }
            if (binding.emailInput.text.isEmpty() && binding.passwordInput.text.isEmpty()) {
                Toast.makeText(context, "Email and password are empty", Toast.LENGTH_SHORT).show()
            }
            if (!binding.emailInput.text.isEmpty() && !binding.passwordInput.text.isEmpty()){
                viewModel.login(binding.emailInput.text.toString(), binding.passwordInput.text.toString(), auth)
                    .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                        val intent: Intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    })
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
//            val database = Firebase.database
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")
//            view?.findNavController()?.navigate(R.id.action_loginFragment_to_moviesListFragment)
//            reload();
        }
    }


}