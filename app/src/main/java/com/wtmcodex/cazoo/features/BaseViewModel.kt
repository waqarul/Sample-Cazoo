package com.wtmcodex.cazoo.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wtmcodex.cazoo.core.extentions.asLiveData

abstract class BaseViewModel : ViewModel() {
    protected val _showErrorAlertDialog = MutableLiveData<Boolean>()
    val showErrorAlertDialog = _showErrorAlertDialog.asLiveData()
}