package com.edtechgrademy.com.grademy.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.databinding.ActivityMoreBinding
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter

class MoreActivity : AppCompatActivity() {

    lateinit var binding : ActivityMoreBinding
    lateinit var rvMore : RecyclerView
    lateinit var ivSearch : ImageView
    lateinit var ivBack : ImageView
    lateinit var tvTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setUpRecyclerView()

    }

    private fun init() {
        rvMore = findViewById(R.id.rvMore)
        ivSearch = findViewById(R.id.ivSearch)
        ivBack = findViewById(R.id.ivBack)
        tvTitle = findViewById(R.id.tvTitle)
    }

    private fun setUpRecyclerView() {
        val layoutMgr = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        rvMore.layoutManager = layoutMgr
        rvMore.adapter = SubjectRecyclerViewAdapter(this, getPdfs())
    }

    private fun getPdfs(): ArrayList<PdfModel> {
        val list = ArrayList<PdfModel>()
        for(i in 0..50){
            list.add(PdfModel("$i", "thumb $i", "Url $1"))
        }
        return list
    }

}