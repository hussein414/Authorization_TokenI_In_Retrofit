package com.example.authorizationtokeninretrofit.data.service

import com.example.authorizationtokeninretrofit.data.model.ServerResponse
import com.example.authorizationtokeninretrofit.util.Constance
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constance.BASE_URL)
    suspend fun getDetails(): Response<ServerResponse>
}