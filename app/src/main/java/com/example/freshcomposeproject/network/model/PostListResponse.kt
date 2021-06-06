package com.example.freshcomposeproject.network.model

import kotlinx.serialization.Serializable

@Serializable
class PostListResponse(
    val result:ArrayList<Post>,
    val nextPage:Int
)