package com.example.freshcomposeproject.view_models

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.freshcomposeproject.network.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreatePostViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(arrayListOf<Uri>())
    val uiState: StateFlow<ArrayList<Uri>> = _uiState

    fun addPhoto(uri: Uri){
        val uris = arrayListOf(uri)
        uris.addAll(uiState.value)
        _uiState.value = uris
    }
}