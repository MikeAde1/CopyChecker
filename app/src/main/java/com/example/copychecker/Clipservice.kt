package com.example.copychecker

import android.app.Service
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.example.copychecker.utils.SessionManager

class Clipservice : Service() {
    private var notes: MutableList<String> = ArrayList()
    lateinit var clipboard: ClipboardManager
    val context : Context = this
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (((clipboard.hasPrimaryClip()) || clipboard.getPrimaryClipDescription()!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
            var pasteData: String = ""
            val item= clipboard.primaryClip?.getItemAt(0)

            pasteData = item.toString()
            if (!notes.contains(pasteData)) {
                notes.add(pasteData)
                //_intent = Intent(this,MainActivity::class.java)
                //
                //val note = notes.toList()
                //_intent.putExtra("note",ArrayList(notes))
                val sessionManager = SessionManager(context)
                sessionManager.saveNotes("note", ArrayList(notes))
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        Toast.makeText(context, "here", LENGTH_LONG).show()
    }
}



