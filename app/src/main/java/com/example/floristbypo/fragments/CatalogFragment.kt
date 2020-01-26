package com.example.floristbypo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.R
import com.example.floristbypo.adapters.CatalogAdapter
import com.example.floristbypo.databinding.FragmentCatalogBinding
import com.example.floristbypo.repo.Utils
import com.example.floristbypo.viewmodels.CatalogViewModel
import kotlinx.android.synthetic.main.fragment_catalog.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CatalogFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CatalogFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CatalogFragment : Fragment() {
    companion object {
        fun newInstance()= CatalogFragment()
    }

    private lateinit var viewmodel:CatalogViewModel
    private lateinit var viewDataBinding:FragmentCatalogBinding
    private lateinit var listAdapter:CatalogAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        viewDataBinding= FragmentCatalogBinding.inflate(inflater,container,false).apply {
        vModel=viewmodel
            refreshLayout.isRefreshing=true
            refreshLayout.setOnClickListener {
                bindList()
            }
        }

        bindList()
        return viewDataBinding.root
    }

    private fun bindList(){
        listAdapter = CatalogAdapter()
        viewDataBinding.catalogRecycler.adapter=listAdapter
        viewmodel.itemList.observe(this,Observer{list->

                listAdapter.setCatalogList(list)
                refreshLayout.isRefreshing=false


        })
    }

}
