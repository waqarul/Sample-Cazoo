package com.wtmcodex.cazoo.features.home.viewitem

sealed class IViewItem
data class HeaderViewItem(val title: String, val subTitle: String) : IViewItem()
data class ProductAvailableViewItem(
    val title: String,
    val date: String,
    val description: String,
    val price: Double,
    val currency: String,
    val rating: Float,
    val imageUrl: String,
    val isBookmarked: Boolean = false,
    val onClickAction: (() -> Unit)?
) : IViewItem()

data class ProductNotAvailableViewItem(
    val title: String,
    val description: String,
    val rating: Float,
    val imageUrl: String,
    val isBookmarked: Boolean = false,
    val onClickAction: (() -> Unit)?
) : IViewItem()

data class FooterViewItem(
    val title: String? = null,
    val onClickAction: (() -> Unit)? = null
) : IViewItem()

data class ErrorViewItem(
    val title: String? = null,
    val subTitle: String? = null,
    val onClickAction: (() -> Unit)?
) : IViewItem()