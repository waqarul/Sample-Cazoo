package com.wtmcodex.cazoo.features.home

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.wtmcodex.cazoo.R
import com.wtmcodex.cazoo.core.data.enum.FilterProductEnum
import com.wtmcodex.cazoo.core.data.model.AlertModel
import com.wtmcodex.cazoo.core.data.model.CazooProductModel
import com.wtmcodex.cazoo.databinding.FragmentHomeBinding
import com.wtmcodex.cazoo.features.BaseFragment
import com.wtmcodex.cazoo.features.home.adapter.HomeProductRecyclerViewAdapter
import com.wtmcodex.cazoo.features.home.viewitem.IViewItem
import com.wtmcodex.cazoo.helpers.Utils

class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    override fun getBindingView(inflater: LayoutInflater?): View {
        binding = FragmentHomeBinding.inflate(inflater!!)
        return binding!!.root
    }

    override fun getOrCreateViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactoryProvider).get(
            HomeViewModel::class.java
        )
    }

    override fun setupUI() {
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    override fun setupBindings() {

        with(viewModel) {
            isLoading.observe(viewLifecycleOwner, { isLoading: Boolean? ->
                if (isLoading!!) {
                    binding!!.pbLoading.visibility = View.VISIBLE
                } else {
                    binding!!.pbLoading.visibility = View.GONE
                }
            })
            showRefreshIndicator.observe(
                viewLifecycleOwner,
                { showRefreshIndicator: Boolean? ->
                    binding!!.srLayout.isRefreshing = showRefreshIndicator!!
                })
            isRecordFound.observe(viewLifecycleOwner, { isRecordFound: Boolean? ->
                if (isRecordFound!!) {
                    binding!!.tvNoResult.visibility = View.GONE
                    binding!!.rvProducts.visibility = View.VISIBLE
                } else {
                    binding!!.tvNoResult.visibility = View.VISIBLE
                    binding!!.rvProducts.visibility = View.GONE
                }
            })
            viewItems.observe(
                viewLifecycleOwner,
                { viewItems: List<IViewItem> ->
                    val adapter =
                        binding!!.rvProducts.adapter as HomeProductRecyclerViewAdapter?
                    adapter!!.setViewItems(viewItems)
                })
            showErrorAlertDialog.observe(viewLifecycleOwner, { shouldShowErrorAlert ->
                if (shouldShowErrorAlert) {
                    showAlert(
                        AlertModel(
                            title = requireActivity().getString(R.string.title_error_in_request),
                            message = requireActivity().getString(R.string.message_error_in_request),
                            positiveButtonTitle = requireActivity().getString(R.string.alert_ok_label)
                        )
                    )
                }

            })

            navigateToProductDetail.observe(
                viewLifecycleOwner,
                { product ->
                    val item: CazooProductModel? = product?.getContentIfNotHandled()
                    if (item != null) {
                        Navigation.findNavController(binding!!.root).navigate(
                            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(item)
                        )
                    }
                })
        }
    }

    override fun setListeners() {
        binding!!.srLayout.setOnRefreshListener {
            binding!!.srLayout.isRefreshing = true
            doOnRefresh()
        }
    }

    override fun loadData() {

        doApiCall(true)
    }

    private fun setupRecyclerView() {
        binding!!.rvProducts.adapter = HomeProductRecyclerViewAdapter()
        binding!!.rvProducts.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupSwipeRefreshLayout() {
        binding!!.srLayout.setColorSchemeColors(
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_blue_bright),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_green_light),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_orange_light),
            ContextCompat.getColor(binding!!.srLayout.context, android.R.color.holo_red_light)
        )
    }

    private fun doOnRefresh() {
        doApiCall(false)
    }

    private fun doApiCall(showLoading: Boolean) {
        if (!Utils.isNetworkAvailable(requireContext())) {
            showAlert(
                AlertModel(
                    title = requireActivity().getString(R.string.title_no_internet_connection),
                    message = requireActivity().getString(R.string.message_no_internet_connection),
                    positiveButtonTitle = requireActivity().getString(R.string.alert_ok_label)
                )
            )

            binding!!.pbLoading.visibility = View.GONE
            binding!!.tvNoResult.visibility = View.VISIBLE
            binding!!.rvProducts.visibility = View.GONE
            binding!!.srLayout.isRefreshing = false
            return
        }
        viewModel.loadData(showLoading)
    }
}