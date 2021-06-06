package com.example.freshcomposeproject.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.freshcomposeproject.network.model.Post

class MovieSource(
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val nextPage = params.key ?: 0
            val postListResponse = ApiService.getPosts(nextPage)

            LoadResult.Page(
                data = postListResponse.result,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = postListResponse.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        TODO("Not yet implemented")
    }
}