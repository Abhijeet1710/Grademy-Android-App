package com.edtechgrademy.com.grademy.view

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.databinding.ActivityDashboardBinding
import com.edtechgrademy.com.grademy.view.fragment.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder

class DashboardActivity : AppCompatActivity() {

    lateinit var binding : ActivityDashboardBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var toolBar : Toolbar
    lateinit var slidingRootNav : SlidingRootNav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        findViewById<LinearLayout>(R.id.btnHome).setOnClickListener {
            makeSelectedActive(R.id.tvHome, R.id.tvProfile, R.id.tvSetting)
            changeMainScreenFragmentTo("Home")
        }
        findViewById<LinearLayout>(R.id.btnProfile).setOnClickListener {
            makeSelectedActive(R.id.tvProfile, R.id.tvHome, R.id.tvSetting)
            changeMainScreenFragmentTo("Profile")

        }
        findViewById<LinearLayout>(R.id.btnSetting).setOnClickListener {
            makeSelectedActive(R.id.tvSetting, R.id.tvProfile, R.id.tvHome)
            changeMainScreenFragmentTo("Settings")

        }

        findViewById<LinearLayout>(R.id.btnLogOut).setOnClickListener {
            mAuth.signOut()
//            showDlg() to confirm logout
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }

    private fun init() {
        toolBar = findViewById(R.id.toolbar)
        setUpDrawer()
        mAuth = FirebaseAuth.getInstance()
    }

    private fun changeMainScreenFragmentTo(to : String) {
        toolBar.title = to
//        set new fragment
        when(to.toLowerCase()) {
            "home" -> toFrag(HomeFragment())
            "setting" -> toFrag(HomeFragment())
            "profile" -> toFrag(HomeFragment())
        }
    }

    private fun toFrag(frag : Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flContainer, frag)
        ft.commit()
        slidingRootNav.closeMenu()

    }

    private fun makeSelectedActive(active: Int, nA1: Int, nA2: Int) {
        findViewById<TextView>(active).setTypeface(null, Typeface.BOLD)
        findViewById<TextView>(nA1).setTypeface(null, Typeface.NORMAL)
        findViewById<TextView>(nA2).setTypeface(null, Typeface.NORMAL)
    }


    private fun setUpDrawer() {
        toolBar.title = "Home"
        setSupportActionBar(toolBar)
        slidingRootNav = SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolBar)
            .withMenuOpened(false)
            .withMenuLayout(R.layout.drawer_menu)
            .withGravity(SlideGravity.LEFT)
            .inject()

    }


}

