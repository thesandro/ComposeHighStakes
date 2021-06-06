package com.example.freshcomposeproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.freshcomposeproject.R
import com.example.freshcomposeproject.network.ApiService
import com.example.freshcomposeproject.network.model.Post
import com.example.freshcomposeproject.ui.composables.ErrorItem
import com.example.freshcomposeproject.ui.composables.LoadingItem
import com.example.freshcomposeproject.ui.composables.LoadingView
import com.example.freshcomposeproject.ui.navigation.Screen
import com.example.freshcomposeproject.ui.theme.FreshComposeProjectTheme
import com.example.freshcomposeproject.view_models.PostsViewModel
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch


@Composable
fun HomeView(
    navController: NavHostController,
    postsViewModel: PostsViewModel = viewModel()
) {
    val posts: LazyPagingItems<Post> = postsViewModel.posts.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier =
            Modifier
                .background(color = Color.Gray)
                .fillMaxHeight()
                .fillMaxWidth(),
            contentPadding = PaddingValues(0.dp, vertical = 0.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            item {
                FeedHeader(navController = navController)
            }
            items(posts) { post ->
                Post(post!!)
                posts.apply {
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            LoadingView(modifier = Modifier.fillParentMaxSize())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeedHeader(navController: NavHostController) {

    fun navigateToCreatePost(){
        navController.navigate(Screen.CreatePost.route)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp, 2.dp, 10.dp, 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop,
            painter = rememberCoilPainter(
                request = "http://proofof.dog/static/11cf6c18151cbb22c6a25d704ae7b313/9195b/doge-main.webp",
                previewPlaceholder = R.drawable.ic_launcher_foreground
            ),
            contentDescription = ""
        )

        Button(
            onClick = {
                navigateToCreatePost()
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                .background(Color.White)
                .border(1.dp, Color.Gray, RoundedCornerShape(50)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            )
        )
        {
            Text("Do you want to make a post?",textAlign = TextAlign.Start,modifier = Modifier.fillMaxWidth(), color = Color.Gray)
        }
    }
}

@Composable
fun Post(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop,
                painter = rememberCoilPainter(
                    request = "https://www.kickassfacts.com/wp-content/uploads/2021/04/bear.jpg",
                    previewPlaceholder = R.drawable.ic_launcher_foreground
                ),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                text = post.full_name
            )
        }
        Text(
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 5.dp),
            text = post.description
        )
        if (post.urls.isNotEmpty()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    .requiredWidth(with(LocalDensity.current) { post.urls[0].width.toDp() })
                    .requiredHeight(with(LocalDensity.current) { post.urls[0].height.toDp() })
                    .background(color = colorResource(R.color.post_color)),
                painter = rememberCoilPainter(
                    request = post.urls[0].url,
                    previewPlaceholder = R.drawable.ic_launcher_foreground
                ),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
        }
    }
}