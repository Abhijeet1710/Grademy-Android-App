package com.edtechgrademy.com.grademy.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.activity.MoreActivity
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter
import com.google.android.material.circularreveal.cardview.CircularRevealCardView


class HomeFragment : Fragment() {

    private lateinit var vm : DashboardViewModel
    lateinit var adapter: SubjectRecyclerViewAdapter

    lateinit var ctx : Context

    lateinit var svMain : NestedScrollView
    lateinit var pbLoader : CircularRevealCardView

    lateinit var rvEnglish : RecyclerView
    lateinit var rvScience1 : RecyclerView
    lateinit var rvScience2 : RecyclerView
    lateinit var rvAlgebra : RecyclerView
    lateinit var rvGeometry : RecyclerView

    lateinit var ivMoreEnglish : ConstraintLayout

    val listEnglish = ArrayList<PdfModel>()
    val listScience1 = ArrayList<PdfModel>()
    val listScience2 = ArrayList<PdfModel>()
    val listAlgebra = ArrayList<PdfModel>()
    val listGeometry = ArrayList<PdfModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        ctx = container!!.context

        init(view)
        vm.getEnglishPdfs {

            for (i in 0..4) {
                listEnglish.add(it[i])
            }

            svMain.visibility = View.VISIBLE
            pbLoader.visibility = View.GONE

//            initRecyclerView(rvEnglish, it)
//            initRecyclerView(rvScience1, it)
//            initRecyclerView(rvScience2, it)
//            initRecyclerView(rvAlgebra, it)
//            initRecyclerView(rvGeometry, it)
        }

        initRecyclerView(rvEnglish)
        initRecyclerView(rvScience1)
        initRecyclerView(rvScience2)
        initRecyclerView(rvAlgebra)
        initRecyclerView(rvGeometry)
        
        ivMoreEnglish.setOnClickListener {
            val intent = Intent(ctx, MoreActivity::class.java)
            intent.putExtra("selected_subject", "English")
            startActivity(intent)
        }


        return view
    }

    private fun init(view: View) {
        val factory = DashboardVMFactory()
        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)

        svMain = view.findViewById(R.id.svMain)
        pbLoader = view.findViewById(R.id.pbLoader)

        rvEnglish = view.findViewById(R.id.rvEnglish)
        rvScience1 = view.findViewById(R.id.rvScience1)
        rvScience2 = view.findViewById(R.id.rvScience2)
        rvAlgebra = view.findViewById(R.id.rvAlgebra)
        rvGeometry = view.findViewById(R.id.rvGeometry)

        ivMoreEnglish = view.findViewById(R.id.ivMoreEnglish)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        adapter = SubjectRecyclerViewAdapter(ctx)
        adapter.setData(listEnglish)
        val layoutMgr = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutMgr
        recyclerView.adapter = adapter
    }


}