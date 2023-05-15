package com.example.authorizationtokeninretrofit.data.repository

import com.example.authorizationtokeninretrofit.data.service.ApiService
import com.example.authorizationtokeninretrofit.util.toResultFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {
    fun getDetails() = toResultFlow {
        apiService.getDetails()
    }
}