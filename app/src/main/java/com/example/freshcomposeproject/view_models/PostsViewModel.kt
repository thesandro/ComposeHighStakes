package com.example.freshcomposeproject.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.freshcomposeproject.network.ApiService
import com.example.freshcomposeproject.network.MovieSource
import com.example.freshcomposeproject.network.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    val posts: Flow<PagingData<Post>> = Pager(PagingConfig(pageSize = 5)) {
        MovieSource()
    }.flow
}