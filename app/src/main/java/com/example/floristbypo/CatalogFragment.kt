package com.example.floristbypo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.floristbypo.adapters.CatalogAdapter
import com.example.floristbypo.databinding.CatalogItemBinding
import com.example.floristbypo.databinding.FragmentCatalogBinding
import com.example.floristbypo.databinding.UserProfileFragmentBinding
import com.example.floristbypo.models.Catalog
import com.example.floristbypo.viewmodels.CatalogViewModel
import com.example.floristbypo.viewmodels.UserProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore


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
        fun newInstance()=CatalogFragment()
    }

    private lateinit var viewmodel:CatalogViewModel
    private lateinit var viewDataBinding:FragmentCatalogBinding
    private lateinit var listAdapter:CatalogAdapter
    private  var catalogs: LiveData<List<Catalog>> = MutableLiveData<List<Catalog>>().apply { value = mutableListOf()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        getCatalog()
        viewmodel.items=catalogs
        viewDataBinding= FragmentCatalogBinding.inflate(inflater,container,false).apply {
            vModel=viewmodel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setUpListAdapter()
    }

    private fun setUpListAdapter(){
        val viewModel=viewDataBinding.vModel
        if(viewModel!=null) {
            listAdapter = CatalogAdapter(viewModel)
            //catalogRecycler = recyclerViewName
            viewDataBinding.catalogRecycler.adapter=listAdapter
        }
    }

    fun getCatalog(){
        val database = FirebaseFirestore.getInstance()
    //catalogs=MutableLiveData<List<Catalog>>().apply { value = mutableListOf()}
        database.collection("Catalog")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    catalogs = MutableLiveData<List<Catalog>>().apply { value = result.toObjects(Catalog::class.java) }
                var catalog=document.toObject(Catalog::class.java)
                  catalogs = MutableLiveData<List<Catalog>>().apply { value = mutableListOf(catalog)}
                }

//           Log.d(TAG, "${document.id} => ${document.data}")

            }
            .addOnFailureListener { exception ->
                //                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}
