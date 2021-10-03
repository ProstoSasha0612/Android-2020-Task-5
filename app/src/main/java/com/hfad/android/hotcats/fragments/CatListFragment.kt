package com.hfad.android.hotcats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.android.hotcats.CatAdapter
import com.hfad.android.hotcats.data.CatApi
import com.hfad.android.hotcats.data.CatApiImpl
import com.hfad.android.hotcats.viemodels.CatListViewModel
import com.hfad.android.hotcats.databinding.CatListFragmentBinding
import com.hfad.android.hotcats.model.Cat
import com.hfad.android.hotcats.viemodels.CatListViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CatListFragment() : Fragment() {

    private lateinit var viewModel: CatListViewModel
    private var _binding: CatListFragmentBinding? = null
    private val binding
        get() = requireNotNull(_binding)
    private var catAdapter: CatAdapter? = null
    private var catLayoutManager: LinearLayoutManager? = null
    private lateinit var catList: MutableList<Cat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, CatListViewModelFactory(CatApiImpl.catService)).get(
            CatListViewModel::class.java
        )
        _binding = CatListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catAdapter = CatAdapter(requireContext())
        catLayoutManager = LinearLayoutManager(context)

        binding.rvCats.apply {
            layoutManager = catLayoutManager
            adapter = catAdapter
        }

        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.catList.collectLatest {
                catAdapter?.submitData(it)
            }
        }

        catAdapter?.addLoadStateListener { state: CombinedLoadStates ->
            binding.rvCats.isVisible = state.refresh != LoadState.Loading
            binding.progress.isVisible = state.refresh == LoadState.Loading
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = CatListFragment()
    }
}
