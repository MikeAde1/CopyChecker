package com.example.copychecker.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.copychecker.R
import com.example.copychecker.net.RetrofitInstance
import com.example.copychecker.model.ClipObject
import com.example.copychecker.net.Api


class LoginActivity : AppCompatActivity() {

    private lateinit var retrofitInstance: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        retrofitInstance = RetrofitInstance().getRetrofitInstance()
            .create(Api::class.java)

       /* val call = retrofitInstance.getAllPhotos()
        call.enqueue( object : Callback<List<ClipObject>> {
            override fun onFailure(call: Call<List<ClipObject>>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"displayed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ClipObject>>, response: Response<List<ClipObject>>) {

            }

        })*/
        /*button.setOnClickListener {
            BiometricManager.BiometricBuilder(this@LoginActivity)
                .setTitle("Add a title")
                .setSubtitle("Add a subtitle")
                .setDescription("Add a description")
                .setNegativeButtonText("Add a cancel button")
                .build()
                .authenticate(biometricCallback)
        }*/

    }

    private fun generateDataList(body: List<ClipObject>) {

    }
}
