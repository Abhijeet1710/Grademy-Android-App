package com.edtechgrademy.com.grademy.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.databinding.ActivityDashboardBinding
import com.edtechgrademy.com.grademy.view.fragment.HomeFragment
import com.edtechgrademy.com.grademy.view.fragment.ProfileFragment
import com.edtechgrademy.com.grademy.view.fragment.SettingsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder

class DashboardActivity : AppCompatActivity() {

    lateinit var binding : ActivityDashboardBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var toolBar : Toolbar
    lateinit var slidingRootNav : SlidingRootNav
    lateinit var dlgBuilder : MaterialAlertDialogBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        findViewById<LinearLayout>(R.id.btnHome).setOnClickListener {
            makeSelectedActive(R.id.hAct, R.id.pAct, R.id.sAct)
            changeMainScreenFragmentTo("Home")
        }
        findViewById<LinearLayout>(R.id.btnProfile).setOnClickListener {
            makeSelectedActive(R.id.pAct, R.id.hAct, R.id.sAct)
            changeMainScreenFragmentTo("Profile")

        }
        findViewById<LinearLayout>(R.id.btnSetting).setOnClickListener {
            makeSelectedActive(R.id.sAct, R.id.hAct, R.id.pAct)
            changeMainScreenFragmentTo("Settings")

        }

        findViewById<LinearLayout>(R.id.btnLogOut).setOnClickListener {
//            showDlg() to confirm logout
            dlgBuilder.show()
        }

    }

    private fun createDlg() {
        dlgBuilder.setTitle("Log Out!")
        dlgBuilder.setMessage("Are you sure, Want to Log out ?")
//        dlgBuilder.background = resources.getDrawable(R.drawable.dlg_back, null)
        dlgBuilder.setPositiveButton("Log out", ) { _: DialogInterface, _: Int ->
            mAuth.signOut()
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        dlgBuilder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
        }
    }

    private fun init() {
        toolBar = findViewById(R.id.toolbar)
        setUpDrawer()
        mAuth = FirebaseAuth.getInstance()
        dlgBuilder = MaterialAlertDialogBuilder(this)
        createDlg()
        changeMainScreenFragmentTo("Home")
    }

    private fun changeMainScreenFragmentTo(to : String) {
        toolBar.title = to
//        set new fragment
        when(to.toLowerCase()) {
            "home" -> toFrag(HomeFragment())
            "profile" -> toFrag(ProfileFragment())
            "settings" -> toFrag(SettingsFragment())
        }
    }

    private fun toFrag(frag : Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flContainer, frag)
        ft.commit()
        slidingRootNav.closeMenu()

    }

    private fun makeSelectedActive(active: Int, nA1: Int, nA2: Int) {
        findViewById<ImageView>(active).visibility = View.VISIBLE
        findViewById<ImageView>(nA1).visibility = View.GONE
        findViewById<ImageView>(nA2).visibility = View.GONE

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

