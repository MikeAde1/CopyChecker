package com.example.copychecker.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.copychecker.model.ClipObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SessionManager(ctx : Context) {
    private val sharedPreference= ctx.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
    var edit: SharedPreferences.Editor
    init {
        edit = with(sharedPreference) { return@with edit() }
    }
    fun saveNotes(key:String="note", notes: ArrayList<String>){
        edit.clear()
        val gson = Gson()
        val g = gson.toJson(notes)
        edit.putString(key,g).apply {  }
    }

    fun getNotes(): ArrayList<String>{
        val gson = Gson()
        val note = sharedPreference.getString("note","empty")
        val itemType = object :TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(note, itemType)
    }

    fun saveClipObject(key:String="notes", notes: List<ClipObject>? = null){
        edit.clear()
        val gson = Gson()
        val g = gson.toJson(notes)
        edit.putString(key,g).apply()
    }

    fun getClipObject(): List<ClipObject>{
        val gson = Gson()
        val note = sharedPreference.getString("notes",null)
        val itemType = object :TypeToken<List<String>>() {}.type
        return gson.fromJson(note, itemType)
    }
}