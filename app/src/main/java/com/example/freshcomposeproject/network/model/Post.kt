package com.example.freshcomposeproject.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Post(
    val id:String,
    val owner:String,
    val title:String,
    val description:String,
    val category_id:Int,
    val urls:ArrayList<ImageUrl>,
    val tags:String,
    val price:Double,
    val price_type:String,
    val full_name:String,
){
    @Serializable
    class ImageUrl(
        val url:String,
        @SerialName("image_height")
        val height:Int,
        @SerialName("image_width")
        val width:Int,
        val format:String
    )
}