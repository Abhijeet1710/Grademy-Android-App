package com.edtechgrademy.com.grademy.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.controller.DashboardVMFactory
import com.edtechgrademy.com.grademy.controller.DashboardViewModel
import com.edtechgrademy.com.grademy.model.CurrentUser
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private lateinit var vm : DashboardViewModel
    private lateinit var user : CurrentUser

    private lateinit var userName : TextView
    private lateinit var userEmail : TextView
    private lateinit var userPhone : TextView
    private lateinit var userProfilePic : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val factory = DashboardVMFactory()
        vm = ViewModelProvider(this, factory).get(DashboardViewModel::class.java)
        init(view)

        loadUserOnUi()

        return view
    }

    private fun init(view: View) {
        userName = view.findViewById(R.id.tvUserName)
        userEmail = view.findViewById(R.id.tvUserEmail)
        userPhone = view.findViewById(R.id.tvPhoneNo)
        userProfilePic = view.findViewById(R.id.ivProfile)
    }

    private fun loadUserOnUi() {
        user = vm.getUser()
        userName.text = user.name
        userEmail.text = user.email
        userPhone.text = if(user.phoneNo != "" || user.phoneNo != null) user.phoneNo else "+91 7558348607"
        Picasso.get().load(user.photoUrl).into(userProfilePic)

    }

}