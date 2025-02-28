package com.kaushal.composecarouselapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppContent(viewModel: MainActivityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = listState.firstVisibleItemIndex) {
        viewModel.updateAutoScrollEnabled(listState.firstVisibleItemIndex == 0)
    }

    Column {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, _ ->
                        viewModel.updateAutoScrollEnabled(false)
                    }
                }
        ) {

            item {
                Carousel(
                    items = DataRepository.getCarouselItem(),
                    currentIndex = viewModel.currentCarouselIndex,
                    onNextClicked = {
                        viewModel.updateNextItem()
                        viewModel.updateAutoScrollEnabled(false)
                    },
                    onPreviousClicked = {
                        viewModel.updatePreviousItem()
                        viewModel.updateAutoScrollEnabled(false)
                    }
                )
            }

            items(viewModel.listItems.size) {
                ListItemCard(item = viewModel.listItems[it])
            }
        }
    }
}

@Composable
fun ListItemCard(item: ListItem) {
    // list item card view
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
    ) {
        Column {
            Image( // list item image
                painter = painterResource(id = item.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text( // list item text below Image
                text = item.text,
                Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}