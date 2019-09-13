package com.example.copychecker.net

import com.example.copychecker.model.ClipObject
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface Api {
    @GET("photos")
    suspend fun getAllPhotos(): Response<List<ClipObject>>

   @GET("photos")
   fun getPhotos(): Call<List<ClipObject>>
}