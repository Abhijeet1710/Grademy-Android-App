package com.edtechgrademy.com.grademy.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.model.NoteModel
import com.edtechgrademy.com.grademy.view.helpers.SubjectRecyclerViewAdapter


class HomeFragment : Fragment() {

    lateinit var ctx : Context
    lateinit var rvSubject : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        ctx = container!!.context
        init(view)
        initRecyclerView()

        return view
    }

    private fun init(view: View) {
        rvSubject = view.findViewById(R.id.rvSubject)
    }

    private fun initRecyclerView() {
        val layoutMgr = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        rvSubject.layoutManager = layoutMgr
        rvSubject.adapter = SubjectRecyclerViewAdapter(ctx, getNotes())
    }

    private fun getNotes() : ArrayList<NoteModel> {
        val list = ArrayList<NoteModel>()
        for(i in 0..5){
            list.add(NoteModel("$i", "thumb $i", "Url $1"))
        }
        return list
    }

}