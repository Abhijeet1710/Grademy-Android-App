package com.edtechgrademy.com.grademy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.view.adapter.DrawerAdapter
import com.edtechgrademy.com.grademy.view.adapter.OnItemClicked
import com.google.firebase.auth.FirebaseAuth
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder

class DashboardActivity : AppCompatActivity(), OnItemClicked {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var accountsRecyclerView: RecyclerView
    lateinit var adapter : DrawerAdapter
    private lateinit var toolBar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dashboard)

        toolBar = findViewById(R.id.toolbar)
        setUpDrawer()
        mAuth = FirebaseAuth.getInstance()
        accountsRecyclerView = findViewById(R.id.rvDrawerList)
        setUpRecyclerView()

        findViewById<TextView>(R.id.btnLogOut).setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        adapter = DrawerAdapter(getList(), this)
        accountsRecyclerView.layoutManager = layoutManager
        accountsRecyclerView.setHasFixedSize(true)
        accountsRecyclerView.adapter = adapter
    }

    private fun getList(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Home")
        list.add("Settings")
        return list
    }

    private fun setUpDrawer() {
        toolBar.title = "This Is Title"
        setSupportActionBar(toolBar)
        SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolBar)
            .withMenuOpened(false)
            .withMenuLayout(R.layout.drawer_menu)
            .withGravity(SlideGravity.LEFT)
            .inject()
    }


    override fun onItemClickedEvent(item: String) {
        Toast.makeText(this, "$item", Toast.LENGTH_SHORT).show()
//        changeFrag()
    }
}