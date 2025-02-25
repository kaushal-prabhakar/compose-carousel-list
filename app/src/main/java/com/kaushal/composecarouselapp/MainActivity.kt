package com.kaushal.composecarouselapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}


@Preview
@Composable
fun AppContent() {

    // sample carousel items
    val carouselItems = listOf(
        CarouselItem(
            R.drawable.carousel_image_1,
            "Lorem 1 ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
        ),
        CarouselItem(
            R.drawable.carousel_image_2,
            "Lorem 2 ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
        ),
        CarouselItem(
            R.drawable.carousel_image_3,
            "Lorem 3 ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
        ),
        CarouselItem(
            R.drawable.carousel_image_4,
            "Lorem 4 ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
        ),
        CarouselItem(
            R.drawable.carousel_image_5,
            "Lorem 5 ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt."
        )
    )


    // sample list items below carousel item
    val listItems = listOf(
        ListItem(R.drawable.list_item_1, "Lorem 1 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_2, "Lorem 2 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_3, "Lorem 3 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_4, "Lorem 4 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_5, "Lorem 5 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_6, "Lorem 6 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_7, "Lorem 7 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_8, "Lorem 8 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_9, "Lorem 9 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_10, "Lorem 10 ipsum dolor sit amet"),
    )


    var autoScrolledEnabled by rememberSaveable { mutableStateOf(true) }

    // lazy column state to detect scrolling in the recycler view
    val listState = rememberLazyListState()

    // holds the current visible item index in the carousel
    var currentCarouselIndex by remember { mutableIntStateOf(0) }

    // enable auto-scroll only if the first item(carousel) in the list is visible
    LaunchedEffect(listState.firstVisibleItemIndex) {
        autoScrolledEnabled = listState.firstVisibleItemIndex == 0
    }

    Column {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, _ ->
                        autoScrolledEnabled =
                            false // Disable auto-scroll when the user manually scrolls the list
                    }
                }) {

            // carousel view
            item {
                Carousel(
                    items = carouselItems,
                    isAutoScrollEnabled = autoScrolledEnabled,
                    lastIndex = currentCarouselIndex, // pass the last known index of the carousel to resume scrolling
                    onIndexChange = { index, isAutoScroll ->
                        if (!isAutoScroll) autoScrolledEnabled = false
                        currentCarouselIndex = index // save the latest index
                    })
            }

            // Below list items
            items(listItems.size) {
                ListItemCard(item = listItems[it])
            }
        }
    }

}

@Composable
fun Carousel(
    items: List<CarouselItem>,
    isAutoScrollEnabled: Boolean,
    lastIndex: Int, // last known index of the carousel before scrolling stopped
    onIndexChange: (Int, Boolean) -> Unit,
) {

    // current index of the carousel
    var currentIndex by remember {
        mutableIntStateOf(lastIndex)
    }

    // handle auto-scroll when enabled
    LaunchedEffect(key1 = isAutoScrollEnabled) {
        while (isAutoScrollEnabled) {
            delay(3000) // wait for 3 seconds before the next scroll

            // update the next item index
            if (currentIndex < items.size - 1) {
                currentIndex++
            } else currentIndex = 0

            // Notify parent that the index has changed due to auto-scroll
            onIndexChange(currentIndex, true)

            /*coroutineScope.launch {
                listState.animateScrollTo(currentIndex) // scroll to the item of the current index position
            }*/
        }
    }

    // Carousel UI with back/forward navigation buttons
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        // back arrow button
        IconButton(modifier = Modifier.weight(1f), onClick = {
            if (currentIndex > 0) {
                currentIndex--
                onIndexChange(currentIndex, false) // update index on manual navigation
                /*coroutineScope.launch {
                    listState.animateScrollTo(currentIndex) // scroll to previous item
                }*/
            }
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        // current carousel item with Image and text
        Card(
            modifier = Modifier
                .weight(10f)
                .padding(8.dp)
                .height(200.dp),
            elevation = 4.dp
        ) {
            val currentItem = items[currentIndex]
            Row {
                CarouselItem(item = currentItem)
            }
        }

        // forward arrow button
        IconButton(modifier = Modifier.weight(1f), onClick = {
            if (currentIndex < items.size - 1) {
                currentIndex++
                onIndexChange(currentIndex, false)  // update index on manual navigation
                /* coroutineScope.launch {
                     listState.animateScrollTo(currentIndex) // scroll to next item
                 }*/
            }
        }) {
            Icon(Icons.Default.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun CarouselItem(item: CarouselItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Carousel Image
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(4.dp)),
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(4.dp)) // space between Image and text in the carousel
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.text, fontSize = 12.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .wrapContentSize(),
            )
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

