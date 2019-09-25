package com.example.floristbypo

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.viewmodels.UserProfileViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.sign

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var binding: UserProfileFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        signInBtn.setOnClickListener {
            var intent=Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }

//    fun IsValid()
//    {
//
//    }
}
