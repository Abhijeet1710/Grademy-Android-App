package com.edtechgrademy.com.grademy.view.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.model.NoteModel
import com.squareup.picasso.Picasso

class SubjectRecyclerViewAdapter(val context : Context, val list : ArrayList<NoteModel>) :
    RecyclerView.Adapter<SubjectRecyclerViewAdapter.SubjectRecyclerViewViewHolder>() {

    inner class SubjectRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail = itemView.findViewById<ImageView>(R.id.ivThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(context.resources.getLayout(R.layout.note_item), parent, false)
        return SubjectRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectRecyclerViewViewHolder, position: Int) {
        holder.thumbnail.setOnClickListener {
            Toast.makeText(context, "${list[position].noteName}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int = list.size
}