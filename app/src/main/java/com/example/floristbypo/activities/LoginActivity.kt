package com.example.floristbypo.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.floristbypo.R
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.models.UserList
import com.example.floristbypo.repo.APIInterface
import com.example.floristbypo.repo.Utils
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : AppCompatActivity() {

    lateinit var binding: UserProfileFragmentBinding
    lateinit var apiInterface:APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
                signInBtn.setOnClickListener {
            var intent=Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        if(Build.VERSION.SDK_INT>=23)
            Utils.requestAppPermission(this,Utils.myPermissions)

//        apiInterface = APIClient.client.create(APIInterface::class.java)
//
//        /**
//        GET List Users
//         **/
//        val call2 = apiInterface.doGetUserList("2")
//
//        call2.enqueue(object : Callback<UserList> {
//            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
//
//                val userList = response.body()
//                val text = userList.page
//                val total = userList.total
//                val totalPages = userList.totalPages
//                val datumList = userList.data
//                Toast.makeText(
//                    applicationContext,
//                     "${text} page\n" + total + " total\n" + totalPages + " totalPages\n",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                for (datum in datumList) {
//                    Toast.makeText(
//                        applicationContext,
//                        "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<UserList>, t: Throwable) {
//                call.cancel()
//            }
//        })

//        FirebaseAnalytics.getInstance(this).setCurrentScreen(this,"MainActicity","")

        Utils.firebaseAnalytics= FirebaseAnalytics.getInstance(this)
        val bundle=Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"LogIn_Florist")
        Utils.firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)
    }

}
