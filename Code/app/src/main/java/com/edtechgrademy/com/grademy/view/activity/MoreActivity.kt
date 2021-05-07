package com.edtechgrademy.com.grademy.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.HomeVMFactory
import com.edtechgrademy.com.grademy.controller.HomeViewModel
import com.edtechgrademy.com.grademy.databinding.ActivityMoreBinding
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter
import com.google.android.material.circularreveal.cardview.CircularRevealCardView

class MoreActivity : AppCompatActivity() {

    private lateinit var vm : HomeViewModel

    lateinit var rvMore : RecyclerView
    lateinit var ivSearch : ImageView
    lateinit var ivBack : ImageView
    lateinit var tvTitle : TextView
    lateinit var pbMoreLoader : CircularRevealCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        init()
        vm.getEnglishPdfs {
            pbMoreLoader.visibility = View.GONE
            rvMore.visibility = View.VISIBLE
            setUpRecyclerView(it)
        }

        ivBack.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun init() {
        val factory = HomeVMFactory()
        vm = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        rvMore = findViewById(R.id.rvMore)
        ivSearch = findViewById(R.id.ivSearch)
        ivBack = findViewById(R.id.ivBack)
        tvTitle = findViewById(R.id.tvTitle)
        pbMoreLoader = findViewById(R.id.pbMoreLoader)
    }

    private fun setUpRecyclerView(list : ArrayList<PdfModel>) {
        val layoutMgr = GridLayoutManager(this, 3)
        rvMore.layoutManager = layoutMgr
        rvMore.adapter = SubjectRecyclerViewAdapter(this, list)
    }


}