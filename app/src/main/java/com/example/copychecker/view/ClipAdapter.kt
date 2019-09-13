package com.example.copychecker.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.copychecker.R
import com.example.copychecker.model.ClipObject
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class ClipAdapter(val context: Context, var notes: List<ClipObject>) : RecyclerView.Adapter<ClipAdapter.Noteholder>() {


    //private var notes: MutableList<ClipObject> = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Noteholder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.item_list, p0, false)
            return Noteholder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(p0: Noteholder, pos: Int) {
        p0.textViewTitle.text = notes[pos].title
        p0.textViewDesc.text = notes[pos].id
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context))
        builder.build().load(notes[pos].url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(p0.coverImage)
        //p0.bind(notes[p1])
    }

    fun setNote(noteList: List<ClipObject>){
        this.notes = noteList
        notifyDataSetChanged()
    }

    inner class Noteholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.text_)
        var textViewDesc: TextView = itemView.findViewById(R.id.description)
        var coverImage: CircleImageView = itemView.findViewById(R.id.floatingIcon)
    }

}