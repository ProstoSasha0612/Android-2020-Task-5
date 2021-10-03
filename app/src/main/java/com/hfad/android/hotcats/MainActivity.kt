package com.hfad.android.hotcats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.android.hotcats.databinding.ActivityMainBinding
import com.hfad.android.hotcats.fragments.CatListFragment

class MainActivity : AppCompatActivity() {

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
            .replace(R.id.container,CatListFragment.newInstance())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}