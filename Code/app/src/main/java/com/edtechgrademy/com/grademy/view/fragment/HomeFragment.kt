package com.edtechgrademy.com.grademy.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.view.activity.MoreActivity
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {

    private lateinit var vm : DashboardViewModel

    lateinit var ctx : Context
    lateinit var rvSubject : RecyclerView
    lateinit var ivMore : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        ctx = container!!.context

        val factory = DashboardVMFactory()
        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)

        init(view)
        vm.getPdfs {
            initRecyclerView(it)
        }

        ivMore.setOnClickListener {
//            startActivity(Intent(ctx, MoreActivity::class.java))
        }

        return view
    }

    private fun init(view: View) {
        rvSubject = view.findViewById(R.id.rvSubject)
        ivMore = view.findViewById(R.id.ivMore)
    }

    private fun initRecyclerView(allPdfs : ArrayList<PdfModel>) {
        val layoutMgr = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        rvSubject.layoutManager = layoutMgr
        rvSubject.adapter = SubjectRecyclerViewAdapter(ctx, allPdfs)
    }


}