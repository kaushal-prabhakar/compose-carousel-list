package com.kaushal.composecarouselapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Carousel(
    items: List<CarouselItem>,
    currentIndex: Int,
    onNextClicked: (Boolean) -> Unit,
    onPreviousClicked: (Boolean) -> Unit
) {

    // Carousel UI with back/forward navigation buttons
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        // current carousel item with Image and text
        Card(
            modifier = Modifier
                .height(200.dp),
            elevation = 4.dp
        ) {
            val currentItem = items[currentIndex]
            Row {
                CarouselItem(item = currentItem)
            }
        }

        // back arrow button
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(shape = CircleShape, color = Color.White.copy(alpha = 0.5f)),
            onClick = {
                onPreviousClicked(false)
            }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        // forward arrow button
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(shape = CircleShape, color = Color.Gray.copy(alpha = 0.2f)),
            onClick = {
                onNextClicked(false)
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
                .padding(8.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(4.dp)),
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(4.dp)) // space between Image and text in the carousel
        Column(modifier = Modifier.weight(1f).align(Alignment.Top)) {
            Text(
                text = item.text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .wrapContentSize(),
            )
        }
    }
}