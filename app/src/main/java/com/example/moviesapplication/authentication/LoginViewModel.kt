package com.example.moviesapplication.authentication

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

private const val TAG = "EmailPassword"

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val liveData = MutableLiveData<FirebaseUser>()

    fun login(email: String, password: String, auth: FirebaseAuth): LiveData<FirebaseUser> {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        liveData.value = user
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", it.exception)
                        Toast.makeText(getApplication(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithEmail:success")
//                        val user = auth.currentUser
//                        liveData.value = user
//                        val a = 2
////                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithEmail:failure", task.exception)
//                        Toast.makeText(getApplication(), "Authentication failed.",
//                            Toast.LENGTH_SHORT).show()
////                        updateUI(null)
//                    }

        }
        return liveData
    }

    fun testit() {
        val s = 2
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}