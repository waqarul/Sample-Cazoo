package com.wtmcodex.cazoo.features.productdetail

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.wtmcodex.cazoo.R
import com.wtmcodex.cazoo.databinding.FragmentProductDetailBinding
import com.wtmcodex.cazoo.features.BaseFragment

class ProductDetailFragment : BaseFragment() {
    private var binding: FragmentProductDetailBinding? = null
    private lateinit var viewModel: ProductDetailViewModel
    override fun getBindingView(inflater: LayoutInflater?): View {
        binding = FragmentProductDetailBinding.inflate(inflater!!)
        return binding!!.root
    }


    override fun getOrCreateViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactoryProvider).get(
            ProductDetailViewModel::class.java
        )
    }

    override fun setupUI() {

    }

    override fun setupBindings() {
        with(viewModel) {
            product.observe(viewLifecycleOwner, {
                binding!!.product = it
                binding!!.tvPrice.text =
                    "${requireActivity().getString(R.string.home_price)} ${it.price} ${it.currency}"
                binding!!.rbProduct.rating = it.rating

                binding!!.btnBookmark.text =
                    if (it.isBookMarked) requireActivity().getString(R.string.product_detail_bookmark) else requireActivity().getString(
                        R.string.product_detail_un_bookmark
                    )
                Glide.with(binding!!.ivImage.context)
                    .load(it.imageURL)
                    .placeholder(R.drawable.placeholder)
                    .into(binding!!.ivImage)
            })
        }

    }

    override fun setListeners() {
        binding!!.btnBookmark.setOnClickListener {
            viewModel.bookmarkProduct()
        }
    }

    override fun loadData() {
        val args = ProductDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.updateData(args.product)
    }

}