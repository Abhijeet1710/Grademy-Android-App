package com.edtechgrademy.com.grademy.model

import android.net.Uri

data class CurrentUser(
        val name : String,
        val fullName : String,
        val email : String,
        val photoUrl : Uri
)
