package com.edtechgrademy.com.grademy.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
import com.google.android.material.navigation.NavigationView


class DashboardActivity : AppCompatActivity(){

    private lateinit var binding : ActivityDashboardBinding

    private lateinit var toolBar : Toolbar
    private lateinit var navigationView : NavigationView
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawer : DrawerLayout

    private lateinit var dlgBuilder : MaterialAlertDialogBuilder
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

        vm.changeTheme(this, vm.getTheme(this))

        toolBar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigation)
        drawer = findViewById(R.id.drawer)

        createDlg()
        changeFragment()

        toolBar.setNavigationOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.itemHome -> {
                    vm.currentFrag = HOME
                    changeFragment()
                }
                R.id.itemProfile -> {
                    vm.currentFrag = PROFILE
                    changeFragment()
                }
                R.id.itemSetting -> {
                    vm.currentFrag = SETTINGS
                    changeFragment()
                }
                R.id.itemLogout -> {
                    dlgBuilder.show()
                }
                else -> {
                    Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
                }
            }

            true
        }
    }

    private fun createDlg() {
        dlgBuilder = MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
        dlgBuilder.setTitle("Log Out!")
        dlgBuilder.setMessage("Are you sure, Want to Log out ?")
        dlgBuilder.setCancelable(false)
        dlgBuilder.setPositiveButton("Log out") { _: DialogInterface, _: Int ->
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
        drawer.closeDrawer(GravityCompat.START)
    }


}

