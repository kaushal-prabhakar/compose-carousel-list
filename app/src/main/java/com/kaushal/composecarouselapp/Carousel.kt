package com.kaushal.composecarouselapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
    onNextClicked : (Boolean) -> Unit,
    onPreviousClicked : (Boolean) -> Unit
) {

    // Carousel UI with back/forward navigation buttons
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {

        // back arrow button
        IconButton(modifier = Modifier.background(shape = CircleShape, color = Color.Gray), onClick = {
            onPreviousClicked(false)
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
        IconButton(modifier = Modifier.background(shape = CircleShape, color = Color.Gray), onClick = {
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