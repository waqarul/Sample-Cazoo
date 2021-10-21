package com.wtmcodex.cazoo.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wtmcodex.cazoo.core.data.enum.FilterProductEnum
import com.wtmcodex.cazoo.core.data.model.CazooProductModel
import com.wtmcodex.cazoo.core.data.model.backend.Product
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.core.data.source.repository.CazooProductRepository
import com.wtmcodex.cazoo.core.extentions.asLiveData
import com.wtmcodex.cazoo.core.extentions.isNotNull
import com.wtmcodex.cazoo.features.BaseViewModel
import com.wtmcodex.cazoo.features.Event
import com.wtmcodex.cazoo.features.home.viewitem.*
import com.wtmcodex.cazoo.helpers.Result
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repository: CazooProductRepository) :
    BaseViewModel() {

    var dateFormat = SimpleDateFormat("MM.dd.yyyy")


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _isRecordFound = MutableLiveData<Boolean>()
    val isRecordFound = _isRecordFound.asLiveData()

    private val _showRefreshIndicator = MutableLiveData<Boolean>()
    val showRefreshIndicator = _showRefreshIndicator.asLiveData()

    private val _viewItems = MutableLiveData<List<IViewItem>>()
    val viewItems = _viewItems.asLiveData()

    private val _navigateToProductDetail = MutableLiveData<Event<CazooProductModel>>()
    val navigateToProductDetail = _navigateToProductDetail.asLiveData()

    private val productList = ArrayList<Product>()

    fun loadData(showLoading: Boolean = true) {
        makeRequestToFetchProducts(showLoading)
    }


    private fun makeRequestToFetchProducts(showLoading: Boolean) {
        makeRequest(showLoading)
    }

    private fun makeRequest(showLoading: Boolean) {

        if (showLoading) {
            _isLoading.postValue(true)
        }

        viewModelScope.launch {
            when (val result = repository.getProducts()) {
                is Result.Success -> {
                    if (result.isNotNull() && result.data.isNotNull()) {
                        val productResponse = result.data!!
                        _viewItems.value = getViewItems(productResponse)
                    } else {
                        _viewItems.value = getErrorViewItems()
                    }
                    resetView()
                }
                is Result.Error -> {
                    resetView()
                    _showErrorAlertDialog.postValue(true)
                    _viewItems.value = getErrorViewItems()
                }
                is Result.Loading -> _isLoading.value = true
            }
        }
    }

    private fun resetView() {
        _isRecordFound.value = true
        _isLoading.value = false
        _showRefreshIndicator.value = false
    }

    private fun getViewItems(productResponse: ProductResponse): List<IViewItem> {
        val viewItems = ArrayList<IViewItem>()
        val header = productResponse.header
        viewItems.add(HeaderViewItem(header.headerTitle, header.headerDescription))

        val products = productResponse.products
        if (!products.isNullOrEmpty()) {
            viewItems.addAll(
                products.map { product ->
                    if (product.available) {
                        ProductAvailableViewItem(
                            title = product.name,
                            date = dateFormat.format(product.releaseDate),
                            description = product.description,
                            price = product.price.value,
                            currency = product.price.currency,
                            rating = product.rating.toFloat(),
                            imageUrl = product.imageURL,
                            onClickAction = { navigateToProductDetail(product) }
                        )
                    } else {
                        ProductNotAvailableViewItem(
                            title = product.name,
                            description = product.description,
                            rating = product.rating.toFloat(),
                            imageUrl = product.imageURL,
                            onClickAction = { navigateToProductDetail(product) }
                        )
                    }
                }
            )
        }

        viewItems.add(FooterViewItem())

        return viewItems
    }

    private fun getErrorViewItems(): List<IViewItem> {
        val viewItems = ArrayList<IViewItem>()
        viewItems.add(ErrorViewItem { makeRequest(true) })
        viewItems.add(FooterViewItem())
        return viewItems
    }

    private fun navigateToProductDetail(product: Product) {
        val cazooProductModel = CazooProductModel(
            name = product.name,
            id = product.id,
            color = product.color,
            imageURL = product.imageURL,
            colorCode = product.colorCode,
            available = product.available,
            releaseDate = dateFormat.format(product.releaseDate),
            description = product.description,
            longDescription = product.longDescription,
            rating = product.rating.toFloat(),
            price = product.price.value,
            currency = product.price.currency,
            isBookMarked = false
        )

        _navigateToProductDetail.postValue(Event(cazooProductModel))
    }

}