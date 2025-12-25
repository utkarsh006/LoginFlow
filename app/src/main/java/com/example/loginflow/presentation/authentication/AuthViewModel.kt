package com.example.loginflow.presentation.authentication

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    init {
        observeAuthState()
    }

    private fun observeAuthState() {
        _authState.value =
            if (auth.currentUser != null) {
                AuthState.Authenticated
            } else {
                AuthState.Unauthenticated
            }
    }

    fun login(email: String, password: String) {
        authenticate(email, password) {
            auth.signInWithEmailAndPassword(email, password)
        }
    }

    fun signUp(email: String, password: String) {
        authenticate(email, password) {
            auth.createUserWithEmailAndPassword(email, password)
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    private fun authenticate(
        email: String,
        password: String,
        authCall: () -> Task<AuthResult>
    ) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }

        _authState.value = AuthState.Loading

        authCall()
            .addOnCompleteListener { task ->
                _authState.value =
                    if (task.isSuccessful) {
                        AuthState.Authenticated
                    } else {
                        AuthState.Error(task.exception?.message ?: "Unknown error")
                    }
            }
    }
}
