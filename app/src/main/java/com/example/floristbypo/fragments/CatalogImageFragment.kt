package com.example.floristbypo.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.floristbypo.viewmodels.CatalogImageViewModel
import com.example.floristbypo.R


class CatalogImageFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogImageFragment()
    }

    private lateinit var viewModel: CatalogImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CatalogImageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
