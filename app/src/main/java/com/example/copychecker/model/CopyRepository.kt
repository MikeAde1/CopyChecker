package com.example.copychecker.model

import android.content.Context
import android.util.Log
import com.example.copychecker.net.Api
import com.example.copychecker.net.RetrofitInstance
import com.example.copychecker.utils.SessionManager
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.Response

class CopyRepository(val context: Context) {
    private lateinit var data: Response<List<ClipObject>>
    private var pair: Pair<Boolean,String> = Pair(true, "")
    private var retrofitInstance: Api
    init {
       retrofitInstance = RetrofitInstance().getRetrofitInstance()
            .create(Api::class.java)
    }

    val sessionManager = SessionManager(context)

    private suspend fun getPosts(): Pair<Boolean, String>{
        CoroutineScope(Dispatchers.IO).launch {

            data = retrofitInstance.getAllPhotos()

            pair = try {
                if (data.isSuccessful){
                    Log.d("RESPONSE",data.body().toString())
                     sessionManager.saveClipObject(notes = data.body())
                    //save
                    Pair(true, "Data fetched successfully")
                }else{
                    Pair(false, "Please check your internet connection")
                }
            }catch (e: JSONException){
                e.printStackTrace()
                Log.d("RESPONSE",data.body().toString())
                Pair(false, "Error with response code ${data.code()}")
            }
        }
        return pair
    }

    fun returnPost() {
        runBlocking {
            getPosts()
        }
    }
}