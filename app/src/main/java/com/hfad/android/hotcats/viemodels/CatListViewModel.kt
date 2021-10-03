package com.hfad.android.hotcats.viemodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.android.hotcats.data.CatApiImpl
import com.hfad.android.hotcats.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatListViewModel : ViewModel() {

    private val _catList = MutableLiveData<MutableList<Cat>>()
    val catList:LiveData<MutableList<Cat>>
        get() = _catList

    fun initList() {
        viewModelScope.launch {
            _catList.value = CatApiImpl.getCats()
        }
    }

    fun addCat() {
        viewModelScope.launch {
            val cat = CatApiImpl.getCat()
            _catList.value?.add(cat)
        }
    }

}