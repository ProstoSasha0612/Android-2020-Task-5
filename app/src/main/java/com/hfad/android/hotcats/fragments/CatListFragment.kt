package com.hfad.android.hotcats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.android.hotcats.CatAdapter
import com.hfad.android.hotcats.viemodels.CatListViewModel
import com.hfad.android.hotcats.databinding.CatListFragmentBinding
import com.hfad.android.hotcats.model.Cat

class CatListFragment : Fragment() {

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
        viewModel = ViewModelProvider(this).get(CatListViewModel::class.java)
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



        //TODO make pagination here
        binding.rvCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val lastVisibleItemPosition = catLayoutManager?.findLastVisibleItemPosition() ?: 0
                val itemCount = (catAdapter?.itemCount?:0) - 2
                if (lastVisibleItemPosition == itemCount) {
                    repeat(5){
                        viewModel.addCat()
                    }
                    catAdapter?.notifyDataSetChanged()
                }
            }
        })

        viewModel.initList()
        viewModel.catList.observe(viewLifecycleOwner) {
            catAdapter?.submitList(it)
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
