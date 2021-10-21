package com.wtmcodex.cazoo.core.data.model


class AlertModel(
    val title: String?,
    val message: String?,
    val positiveButtonTitle: String?,
    val negativeButtonTitle: String? = null,
    val listener: OnAlertClickListener? = null,
) {

    interface OnAlertClickListener {
        fun onPositiveButtonClickAction()
        fun onNegativeButtonClickAction()
    }
}