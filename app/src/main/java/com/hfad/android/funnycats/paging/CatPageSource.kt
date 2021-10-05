package com.hfad.android.funnycats.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hfad.android.funnycats.data.CatApi
import com.hfad.android.funnycats.data.toCat
import com.hfad.android.funnycats.model.Cat
import retrofit2.HttpException

class CatPageSource(
    private val catService: CatApi
) : PagingSource<Int, Cat>() {

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        //video at minute = 20
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page: Int = params.key ?: 1
        val pageSize: Int =
            params.loadSize.coerceAtMost(CatApi.MAX_PAGE_SIZE)

        val response = catService.getCatsList()
        if (response.isSuccessful) {
            val catList: List<Cat> = response.body()?.map { it.toCat() } ?: emptyList()
            val nextKey = if (catList.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(catList, prevKey, nextKey)
        } else { //TODO make error handling
            return LoadResult.Error(HttpException(response))
        }
    }
}