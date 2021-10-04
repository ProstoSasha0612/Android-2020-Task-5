package com.hfad.android.funnycats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.android.funnycats.databinding.ActivityMainBinding
import com.hfad.android.funnycats.fragments.CatDetailsFragment
import com.hfad.android.funnycats.fragments.CatListFragment

class MainActivity : AppCompatActivity(),CatAdapter.OnItemCLickCallback  {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openCatListFragment()
    }


    private fun openCatListFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CatListFragment.newInstance())
            .commit()
    }

    override fun onItemCLickOpenDetails(byteArray: ByteArray) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.animator.back_animator,R.animator.front_animator)
            .replace(R.id.container, CatDetailsFragment.newInstance(byteArray))
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}