package com.edtechgrademy.com.grademy.view.helpers

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.model.PdfModel
import com.squareup.picasso.Picasso

class AllRecyclerViewAdapter(val context : Context) : RecyclerView.Adapter<AllRecyclerViewAdapter.AllRVViewHolder>() {

    var list = ArrayList<PdfModel>()

    inner class AllRVViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val read : Button = itemView.findViewById(R.id.btnRead)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllRVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(context.resources.getLayout(R.layout.note_item_more), parent, false)
        return AllRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllRVViewHolder, position: Int) {
        val pdf = list[position]
        Picasso.get()
            .load(pdf.pdfThumbnail)
            .fit()
            .placeholder(R.drawable.default_placeholder)
            .into(holder.thumbnail)

        holder.title.text = pdf.pdfName.toString()

        holder.read.setOnClickListener {
            val url = pdf.pdfUrl.toString()
            if(isInteretConnected())
                openInDifferentApp(url);
//                openInSameApp(url)
            else{
                Toast.makeText(context, "Check your Internet Connection", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun getItemCount(): Int = list.size

    fun setData(filteredList: ArrayList<PdfModel>) {
        list = filteredList
        notifyDataSetChanged()
    }

    private fun openInDifferentApp(url : String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(
            Uri.parse("$url"),
            "application/pdf")
        context.startActivity(intent)
    }

    private fun isInteretConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val actNetwork : NetworkInfo? = cm.activeNetworkInfo
        return actNetwork?.isConnectedOrConnecting == true
    }

}