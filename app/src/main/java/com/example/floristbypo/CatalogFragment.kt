package com.example.floristbypo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.databinding.FragmentCatalogBinding
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.viewmodels.CatalogViewModel
import com.example.floristbypo.viewmodels.UserProfileViewModel


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
    // TODO: Rename and change types of parameters
    private lateinit var viewmodel:CatalogViewModel
    private lateinit var viewDataBinding:FragmentCatalogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        viewDataBinding= FragmentCatalogBinding.inflate(inflater,container,false).apply {
            viewmodel=viewmodel
        }
        return viewDataBinding.root
    }

}
