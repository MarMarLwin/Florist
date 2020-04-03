package com.example.floristbypo.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.repo.Utils
import com.example.floristbypo.viewmodels.UserProfileViewModel
import kotlinx.android.synthetic.main.user_profile_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class UserProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var viewModel:UserProfileViewModel
    private lateinit var viewDataBinding:UserProfileFragmentBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        //add data from ViewModel
        val now= Calendar.getInstance()
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        viewDataBinding= UserProfileFragmentBinding.inflate(inflater,container,false).apply {

            dobTxt.setOnClickListener {
                val datePicker=DatePickerDialog(context,DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    now.set(Calendar.YEAR, year)
                    now.set(Calendar.MONTH, month)
                    now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    dobTxt.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(now.time)
                },now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
                Utils.firebaseAnalytics.setUserProperty("dob",dobTxt.text.toString())
            }


        }

        viewModel.user.observe(this.viewLifecycleOwner, Observer {
            if(it!=null)
                viewDataBinding.user = it
        })

        return viewDataBinding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
////        viewModel.user.observe(viewLifecycleOwner){
////
////        }
//        userNameTxt.text=viewModel.user.value?.Name
//        genderTxt.text=viewModel.user.value?.Gender
//        dobTxt.text=viewModel.user.value?.DOB
//    }

}
