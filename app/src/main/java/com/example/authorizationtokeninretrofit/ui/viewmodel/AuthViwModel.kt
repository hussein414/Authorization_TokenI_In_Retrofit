package com.example.authorizationtokeninretrofit.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.authorizationtokeninretrofit.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViwModel @Inject constructor(repository: AuthRepository) : ViewModel() {
    val data = repository.getDetails()
}