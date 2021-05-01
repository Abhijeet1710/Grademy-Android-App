package com.edtechgrademy.com.grademy.view.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.view.activity.DashboardActivity
import com.edtechgrademy.com.grademy.view.activity.SignupActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SettingsFragment : Fragment() {

    private lateinit var vm : DashboardViewModel
    private lateinit var ctx : Context
    private lateinit var btnLogout : Button
    private lateinit var rbSystemDefault : RadioButton
    private lateinit var rbDark : RadioButton
    private lateinit var rbLight : RadioButton
    private lateinit var rgTheme : RadioGroup
    private lateinit var dlgBuilder : MaterialAlertDialogBuilder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        ctx = container!!.context

        rgTheme = view.findViewById(R.id.rgTheme)
        rbSystemDefault = view.findViewById(R.id.rbSystemDefault)
        rbDark = view.findViewById(R.id.rbDark)
        rbLight = view.findViewById(R.id.rbLight)
        btnLogout = view.findViewById(R.id.btnLogOut)

        createDlg()
        val factory = DashboardVMFactory()
        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        makeRbChecked()

        rgTheme.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.rbSystemDefault -> { vm.changeTheme(ctx, "System_Default") }
                R.id.rbLight -> { vm.changeTheme(ctx ,"Light") }
                R.id.rbDark -> { vm.changeTheme(ctx, "Dark") }
            }
        }

        btnLogout.setOnClickListener {
            dlgBuilder.show()
        }

        return view
    }


    private fun makeRbChecked() {
        when(vm.getTheme(ctx).toUpperCase()) {
            "DARK" -> { rbDark.isChecked = true }
            "LIGHT" -> { rbLight.isChecked = true }
            "SYSTEM_DEFAULT" -> { rbSystemDefault.isChecked = true }
        }
    }
    private fun createDlg() {
        dlgBuilder = MaterialAlertDialogBuilder(ctx, R.style.AlertDialogTheme)
        dlgBuilder.setTitle("Log Out!")
        dlgBuilder.setMessage("Are you sure, Want to Log out ?")
        dlgBuilder.setCancelable(false)
        dlgBuilder.setPositiveButton("Log out") { _: DialogInterface, _: Int ->
            vm.logOut()
            startActivity(Intent(activity, SignupActivity::class.java))
            activity!!.finish()
        }
        dlgBuilder.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->
        }
    }
}