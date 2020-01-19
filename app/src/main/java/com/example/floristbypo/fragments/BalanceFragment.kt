package com.example.floristbypo.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.floristbypo.R
import com.example.floristbypo.viewmodels.BalanceViewModel

class BalanceFragment : Fragment() {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private lateinit var viewModel: BalanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.balance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BalanceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
