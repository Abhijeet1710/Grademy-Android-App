package com.edtechgrademy.com.grademy.view.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.activity.ViewPdfActivity
import com.squareup.picasso.Picasso

class SubjectRecyclerViewAdapter(val context : Context, private val list : ArrayList<PdfModel>) :
    RecyclerView.Adapter<SubjectRecyclerViewAdapter.SubjectRecyclerViewViewHolder>() {

    inner class SubjectRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(context.resources.getLayout(R.layout.note_item), parent, false)
        return SubjectRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectRecyclerViewViewHolder, position: Int) {

        val pdf = list[position]
        Picasso
            .get()
            .load(pdf.pdfThumbnail)
            .fit()
            .into(holder.thumbnail)

        holder.thumbnail.setOnClickListener {
            Toast.makeText(context, "${pdf.pdfName}", Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(pdf.pdfUrl),
                "application/pdf")
//            intent.putExtra("pdfUrl", pdf.pdfUrl)
//            context.startActivity(intent)

            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = list.size
}