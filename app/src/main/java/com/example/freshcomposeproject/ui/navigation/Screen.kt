package com.example.freshcomposeproject.ui.navigation

import androidx.annotation.StringRes
import com.example.freshcomposeproject.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object Post : Screen("post", R.string.post)
    object Chat : Screen("chat", R.string.chat)
    object Notification : Screen("notifications", R.string.notifications)
    object Login : Screen("login", R.string.login)
    object CreatePost: Screen("create_post",R.string.create_post)


}
