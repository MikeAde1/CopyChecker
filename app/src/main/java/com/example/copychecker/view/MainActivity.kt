package com.example.copychecker.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.copychecker.R
import com.example.copychecker.model.ClipObject
import com.example.copychecker.net.Api
import com.example.copychecker.net.RetrofitInstance
import com.example.copychecker.utils.SessionManager
import com.example.copychecker.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val context: Context = this
    private lateinit var retrofitInstance: Api
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        retrofitInstance = RetrofitInstance().getRetrofitInstance()
            .create(Api::class.java)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val sessionManager = SessionManager(context)

        /*fetch.setOnClickListener{
            val job  = GlobalScope.launch {
                viewModel.getPosts()
            }
            job.start()
            if (job.isActive){
                Toast.makeText(this@MainActivity, "sbshjs", LENGTH_SHORT).show()
            }

            if (job.isCompleted){
               // Toast.makeText(this@MainActivity, "sbshjs", LENGTH_SHORT).show()
                generateDataList(sessionManager.getClipObject())
                Log.e("rrrrrr", sessionManager.getClipObject().toString())
            }*/
            val call = retrofitInstance.getPhotos()
            call.enqueue( object : Callback<List<ClipObject>>{
                override fun onFailure(call: Call<List<ClipObject>>, t: Throwable) {
                    Toast.makeText(context,"displayed", LENGTH_SHORT ).show()
                }
                override fun onResponse(call: Call<List<ClipObject>>, response: Response<List<ClipObject>>) {
                    response.body()?.let { generateDataList(it) }
                }
            })
       /* val intent = Intent(applicationContext, Clipservice::class.java)
        startService(intent)*/

        //Reason why databinding is not used is because the click option will be handle in the viewModel
        //doing this, a progress dialog won't show because view are not allowed to be implemented in viewModel classes


    }

    private fun generateDataList(body: List<ClipObject>) {
        val adapter = ClipAdapter(this, body)
        recyclerView.adapter = adapter
        adapter.setNote(body)
    }

}
