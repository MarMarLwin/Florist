package com.example.floristbypo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.viewmodels.UserProfileViewModel

class UserProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var viewModel:UserProfileViewModel
    private lateinit var viewDataBinding:UserProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        //add data from ViewModel
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        viewDataBinding= UserProfileFragmentBinding.inflate(inflater,container,false).apply {
            viewmodel=viewModel
        }
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
