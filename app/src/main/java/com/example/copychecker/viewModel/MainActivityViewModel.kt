package com.example.copychecker.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import com.example.copychecker.model.ClipObject
import com.example.copychecker.model.CopyRepository

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val copyRepository: CopyRepository = CopyRepository(application.applicationContext)

    fun getPosts(){
        copyRepository.returnPost()
    }
}