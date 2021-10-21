package com.wtmcodex.cazoo.core.extentions


fun Any?.isNotNull(): Boolean {
    return this != null
}

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.doOnNotNull(f: () -> Unit) {
    if (this != null) {
        f()
    }
}
