package com.kaushal.composecarouselapp

object DataRepository {

    fun getCarouselItem() : List<CarouselItem> = listOf(
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

    fun getListItem() : List<ListItem> = listOf(
        ListItem(R.drawable.list_item_1, "Lorem 1 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_2, "Lorem 2 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_3, "Lorem 3 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_4, "Lorem 4 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_5, "Lorem 5 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_6, "Lorem 6 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_7, "Lorem 7 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_8, "Lorem 8 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_9, "Lorem 9 ipsum dolor sit amet"),
        ListItem(R.drawable.list_item_10, "Lorem 10 ipsum dolor sit amet")
    )
}