package com.wtmcodex.cazoo.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wtmcodex.cazoo.R
import com.wtmcodex.cazoo.databinding.*
import com.wtmcodex.cazoo.features.home.viewitem.*

class HomeProductRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewItems: List<IViewItem> = ArrayList()

    fun setViewItems(viewItems: List<IViewItem>) {
        this.viewItems = viewItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.header_card_view_item -> HeaderViewHolder(
                HeaderCardViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.product_available_card_view_item -> ProductAvailableViewHolder(
                ProductAvailableCardViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.product_not_available_card_view_item -> ProductNotAvailableViewHolder(
                ProductNotAvailableCardViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.footer_card_view_item -> FooterViewHolder(
                FooterCardViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.error_card_view_item -> ErrorViewHolder(
                ErrorCardViewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Unhandled view type in onCreateViewHolder HomeProductRecyclerViewAdapter.")
        }
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewItems[position]) {
            is HeaderViewItem -> R.layout.header_card_view_item
            is ProductAvailableViewItem -> R.layout.product_available_card_view_item
            is ProductNotAvailableViewItem -> R.layout.product_not_available_card_view_item
            is FooterViewItem -> R.layout.footer_card_view_item
            is ErrorViewItem -> R.layout.error_card_view_item
            else -> throw IllegalArgumentException("Unhandled view type in getItemViewType HomeProductRecyclerViewAdapter.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val viewItem = viewItems[position]) {
            is HeaderViewItem -> {
                val viewHolder = (holder as HeaderViewHolder)
                viewHolder.bind(viewItem)
            }
            is ProductAvailableViewItem -> {
                val viewHolder = (holder as ProductAvailableViewHolder)
                viewHolder.bind(viewItem)
            }
            is ProductNotAvailableViewItem -> {
                val viewHolder = (holder as ProductNotAvailableViewHolder)
                viewHolder.bind(viewItem)
            }
            is FooterViewItem -> {
                val viewHolder = (holder as FooterViewHolder)
                viewHolder.bind(viewItem)
            }
            is ErrorViewItem -> {
                val viewHolder = (holder as ErrorViewHolder)
                viewHolder.bind(viewItem)
            }
        }
    }

    inner class HeaderViewHolder internal constructor(private val itemBinding: HeaderCardViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewItem: HeaderViewItem) {
            itemBinding.tvTitle.text = viewItem.title
            itemBinding.tvSubTitle.text = viewItem.subTitle
        }
    }

    inner class ProductAvailableViewHolder internal constructor(private val itemBinding: ProductAvailableCardViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewItem: ProductAvailableViewItem) {
            itemBinding.tvTitle.text = viewItem.title
            itemBinding.tvDate.text = viewItem.date
            itemBinding.tvDescription.text = viewItem.description
            itemBinding.tvPrice.text =
                "${itemBinding.tvPrice.context.getString(R.string.home_price)} ${viewItem.price} ${viewItem.currency}"
            itemBinding.rbProduct.rating = viewItem.rating
            itemBinding.container.setOnClickListener { viewItem.onClickAction?.invoke() }

            val color = if (viewItem.isBookmarked) {
                ContextCompat.getColor(
                    itemBinding.container.context,
                    R.color.product_favorite_color
                )
            } else {
                ContextCompat.getColor(itemBinding.container.context, R.color.white)
            }
            itemBinding.container.setCardBackgroundColor(color)

            Glide.with(itemBinding.ivImage.context)
                .load(viewItem.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.ivImage)
        }
    }

    inner class ProductNotAvailableViewHolder internal constructor(private val itemBinding: ProductNotAvailableCardViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewItem: ProductNotAvailableViewItem) {
            itemBinding.tvTitle.text = viewItem.title
            itemBinding.tvDescription.text = viewItem.description
            itemBinding.rbProduct.rating = viewItem.rating
            itemBinding.container.setOnClickListener { viewItem.onClickAction?.invoke() }
            Glide.with(itemBinding.ivImage.context)
                .load(viewItem.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(itemBinding.ivImage)

        }
    }

    inner class FooterViewHolder internal constructor(private val itemBinding: FooterCardViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewItem: FooterViewItem) {
            itemBinding.container.setOnClickListener { viewItem.onClickAction?.invoke() }
        }
    }

    inner class ErrorViewHolder internal constructor(private val itemBinding: ErrorCardViewItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(viewItem: ErrorViewItem) {
            itemBinding.btnError.setOnClickListener { viewItem.onClickAction?.invoke() }
        }
    }
}