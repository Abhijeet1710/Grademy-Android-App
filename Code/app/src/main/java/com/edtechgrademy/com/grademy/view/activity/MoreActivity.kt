package com.edtechgrademy.com.grademy.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.helpers.AllRecyclerViewAdapter
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter
import com.google.android.material.circularreveal.cardview.CircularRevealCardView

class MoreActivity : AppCompatActivity() {

    private lateinit var vm: DashboardViewModel

    private var list = ArrayList<PdfModel>()
    private lateinit var adapter: AllRecyclerViewAdapter

    private lateinit var rvMore: RecyclerView
    private lateinit var ivSearch: ConstraintLayout
    private lateinit var etSearch: EditText
    private lateinit var ivBack: ConstraintLayout
    private lateinit var tvTitle: TextView
    private lateinit var pbMoreLoader: CircularRevealCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more)

        init()
        vm.getEnglishPdfs {
            pbMoreLoader.visibility = View.GONE
            rvMore.visibility = View.VISIBLE
            list = it
            setUpRecyclerView(list)
        }

        ivSearch.setOnClickListener {
            if (etSearch.visibility == View.GONE) showSearchBar()
        }

        ivBack.setOnClickListener {
            if (etSearch.visibility == View.VISIBLE) {
                hideSearchBar()
                loadAllDataIntoList()
            } else {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }



        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })

    }

    private fun filter(text: String) {
        val filteredList = ArrayList<PdfModel>()

        for (item in list) {
            val pdfName = item.pdfName.toString().toLowerCase()
            if (pdfName.contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        adapter.setData(filteredList)
    }

    private fun loadAllDataIntoList() {
        adapter.setData(list)
    }

    private fun showSearchBar() {
        tvTitle.visibility = View.GONE
        ivSearch.visibility = View.GONE
        ivBack.visibility = View.GONE
        etSearch.visibility = View.VISIBLE

    }

    private fun hideSearchBar() {
        etSearch.text.clear()
        etSearch.visibility = View.GONE
        ivSearch.visibility = View.VISIBLE
        tvTitle.visibility = View.VISIBLE
        ivBack.visibility = View.VISIBLE

    }

    override fun onBackPressed() {
        if (etSearch.visibility == View.VISIBLE) {
            hideSearchBar()
            loadAllDataIntoList()
        } else {
           super.onBackPressed()
           finish()
        }
    }

    private fun init() {
        val factory = DashboardVMFactory()
        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)

        rvMore = findViewById(R.id.rvMore)
        ivSearch = findViewById(R.id.ivSearch)
        ivBack = findViewById(R.id.ivBack)
        tvTitle = findViewById(R.id.tvTitle)
        pbMoreLoader = findViewById(R.id.pbMoreLoader)
        etSearch = findViewById(R.id.etSearch)

        tvTitle.text = intent.getStringExtra("selected_subject").toString()
    }

    private fun setUpRecyclerView(list: ArrayList<PdfModel>) {
        adapter = AllRecyclerViewAdapter(this)
        adapter.setData(list)
        val layoutMgr = LinearLayoutManager(this)
        rvMore.layoutManager = layoutMgr
        rvMore.adapter = adapter
    }


}