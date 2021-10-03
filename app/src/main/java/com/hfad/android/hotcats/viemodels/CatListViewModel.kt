package com.hfad.android.hotcats.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hfad.android.hotcats.data.CatApi
import com.hfad.android.hotcats.model.Cat
import com.hfad.android.hotcats.paging.CatPageSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CatListViewModel(private val catApiService: CatApi) : ViewModel() {

    val catList: StateFlow<PagingData<Cat>> = Pager(
        PagingConfig(pageSize = 10)
    ) {
        CatPageSource(catApiService)
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}

class CatListViewModelFactory(val catApiService: CatApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatListViewModel(catApiService) as T
    }
}