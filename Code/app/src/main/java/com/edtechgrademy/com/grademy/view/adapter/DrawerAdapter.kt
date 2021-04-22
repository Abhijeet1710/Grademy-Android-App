package com.edtechgrademy.com.grademy.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.view.DashboardActivity

class DrawerAdapter(private val list: ArrayList<String>, private val listner: DashboardActivity) : RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

//    private var allAccountsList = emptyList<OneAccount>()

    inner class DrawerViewHolder(private val itemView : View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.ivIcon)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val currentItem: LinearLayout = itemView.findViewById(R.id.clCurrentItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        return DrawerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false))
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {

        holder.title.text = list[position]
//        when(list[position]) {
//            "" -> {
//
//            }
//            "" -> {
//
//            }
//            "" -> {
//
//            }
//        }

        holder.currentItem.setOnClickListener {
            listner.onItemClickedEvent(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

}


interface OnItemClicked {
    fun onItemClickedEvent(item: String)
}

