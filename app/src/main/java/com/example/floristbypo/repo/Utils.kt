package com.example.floristbypo.repo

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.analytics.FirebaseAnalytics

class Utils:AppCompatActivity() {
    companion object {
        lateinit var firebaseAnalytics: FirebaseAnalytics

        var myPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET
        )

        fun hasPermission(context: Context, vararg permissions: String): Boolean = permissions.all()
        {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        @TargetApi(Build.VERSION_CODES.M)
        fun requestAppPermission(context: Context, selectPermission: Array<String>) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!hasPermission(context, *myPermissions)) {
                    ActivityCompat.requestPermissions(context as Activity, selectPermission, 1)
                }
            }
        }

        fun showProgressDialog(context: Context?,msg:String):AlertDialog
        {
            val llPadding = 30
            val ll = LinearLayout(context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.setPadding(llPadding, llPadding, llPadding, llPadding)
            ll.gravity = Gravity.CENTER
            var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            llParam.gravity = Gravity.CENTER
            ll.layoutParams = llParam
            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            progressBar.setPadding(0, 0, llPadding, 0)
            progressBar.layoutParams = llParam
            progressBar.setBackgroundColor(Color.TRANSPARENT)

            llParam =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            llParam.gravity = Gravity.CENTER
            val tvText = TextView(context)
            tvText.text = msg
            tvText.setTextColor(Color.parseColor("#000000"))
            tvText.textSize = 20f
            tvText.setBackgroundColor(Color.TRANSPARENT)
            tvText.layoutParams = llParam

            ll.addView(progressBar)
            ll.addView(tvText)
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setView(ll)
            val dialog = builder.create()
            val window = dialog.window
            if (window != null)
            {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window !!.attributes)
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                dialog.window !!.attributes = layoutParams
            }
            return dialog
        }
    }
}