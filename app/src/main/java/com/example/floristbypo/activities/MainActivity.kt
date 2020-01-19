package com.example.floristbypo.activities

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.floristbypo.R
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.fragments.CatalogFragment
import com.example.floristbypo.fragments.UserProfileFragment
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.viewmodels.CatalogViewModel
import com.example.floristbypo.viewmodels.UserProfileViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var TAG="FloristLog"
    private lateinit var binding:UserProfileFragmentBinding
    private lateinit var viewmodel:UserProfileViewModel
    private lateinit var catalog:CatalogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState==null)
        super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
//        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
//        binding.viewmodel= ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // Get token
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result!!.token

                Log.d(TAG, "MSG")
            })
//
//        //Subscription to the topic News
        FirebaseMessaging.getInstance().subscribeToTopic("News")
            .addOnCompleteListener { task ->
                var msg = "Subscribe_Success"//getString(R.string.msg_subscribed)
                if (!task.isSuccessful) {
                    msg ="Subscribe_failed"// getString(R.string.msg_subscribe_failed)
                }
                Log.d(TAG, msg)
            }

    }

    override fun onStart() {
        super.onStart()
//        connectFirestoreDB()
    }

    override fun onResume() {
        super.onResume()
        val notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(0)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_profile -> {
                replaceFragment(UserProfileFragment.newInstance())
            }
            R.id.nav_catalog -> {
                replaceFragment(CatalogFragment.newInstance())
            }
            R.id.nav_message -> {

            }
            R.id.nav_camera -> {

            }
            R.id.nav_balance -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentFrame, fragment)
        fragmentTransaction.commit()
    }

    fun connectRealtimeDB(){
        val database= FirebaseDatabase.getInstance()
        val myRef = database.reference

        val catalogListener = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Catalog::class.java)
                Log.d(TAG, "Value is: $value")            }

        }
        myRef.addValueEventListener(catalogListener)
    }

    fun connectFirestoreDB(){
        val db= FirebaseFirestore.getInstance()
        db.collection("Catalog")
            .get()
            .addOnSuccessListener { result->
                for(document in result)
                    Log.d(TAG, "${document.id} => ${document.data}")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}
