package com.edtechgrademy.com.grademy.view.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.databinding.ActivityDashboardBinding
import com.edtechgrademy.com.grademy.view.fragment.HomeFragment
import com.edtechgrademy.com.grademy.view.fragment.ProfileFragment
import com.edtechgrademy.com.grademy.view.fragment.SettingsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder

class DashboardActivity : AppCompatActivity() {

    lateinit var binding : ActivityDashboardBinding
    private lateinit var toolBar : Toolbar
    lateinit var slidingRootNav : SlidingRootNav
    lateinit var dlgBuilder : MaterialAlertDialogBuilder
    private lateinit var vm : DashboardViewModel

    private val HOME = "Home"
    private val PROFILE = "Profile"
    private val SETTINGS = "Settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = DashboardVMFactory()

        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        toolBar = findViewById(R.id.toolbar)
        dlgBuilder = MaterialAlertDialogBuilder(this)
        setUpDrawer()
        createDlg()
        changeFragment()

        findViewById<LinearLayout>(R.id.btnHome).setOnClickListener {
            makeSelectedActive(R.id.hAct, R.id.pAct, R.id.sAct)
            vm.currentFrag = HOME
            changeFragment()
        }
        findViewById<LinearLayout>(R.id.btnProfile).setOnClickListener {
            makeSelectedActive(R.id.pAct, R.id.hAct, R.id.sAct)
            vm.currentFrag = PROFILE
            changeFragment()

        }
        findViewById<LinearLayout>(R.id.btnSetting).setOnClickListener {
            makeSelectedActive(R.id.sAct, R.id.hAct, R.id.pAct)
            vm.currentFrag = SETTINGS
            changeFragment()

        }
        findViewById<LinearLayout>(R.id.btnLogOut).setOnClickListener {
            slidingRootNav.closeMenu()
            dlgBuilder.show()
        }

    }

    private fun createDlg() {
        dlgBuilder.setTitle("Log Out!")
        dlgBuilder.setMessage("Are you sure, Want to Log out ?")
        dlgBuilder.setPositiveButton("Log out", ) { _: DialogInterface, _: Int ->
           vm.logOut()
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        dlgBuilder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
        }
    }


    private fun changeFragment() {
        toolBar.title = vm.currentFrag
//        set new fragment
        when(vm.currentFrag) {
            HOME -> toFrag(HomeFragment())
            PROFILE -> toFrag(ProfileFragment())
            SETTINGS -> toFrag(SettingsFragment())
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

