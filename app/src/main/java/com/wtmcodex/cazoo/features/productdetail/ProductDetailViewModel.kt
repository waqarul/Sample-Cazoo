package com.wtmcodex.cazoo.features.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wtmcodex.cazoo.core.data.model.CazooProductModel
import com.wtmcodex.cazoo.core.data.source.repository.CazooProductRepository
import com.wtmcodex.cazoo.core.extentions.asLiveData
import com.wtmcodex.cazoo.features.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val repository: CazooProductRepository) :
    BaseViewModel() {
    private lateinit var productItem: CazooProductModel
    private val _product = MutableLiveData<CazooProductModel>()
    val product = _product.asLiveData()

    fun updateData(product: CazooProductModel) {
        viewModelScope.launch {
            productItem = product
            productItem.isBookMarked = repository.isProductBookMarked(product.id)
            _product.value = productItem
        }
    }

    fun bookmarkProduct() {
        viewModelScope.launch {
            productItem.isBookMarked = !productItem.isBookMarked
            repository.shouldBookMarkProduct(productItem)
            _product.value = repository.getProductByID(productItem.id)
        }
    }
}