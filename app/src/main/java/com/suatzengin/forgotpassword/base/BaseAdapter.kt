package com.suatzengin.forgotpassword.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseAdapter<T : Any>(
    areItemsTheSame: (T, T) -> Boolean,
    areContentsTheSame: (T, T) -> Boolean,
) : ListAdapter<T, BaseViewHolder<T>>(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areItemsTheSame(oldItem, newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            areContentsTheSame(oldItem, newItem)
    }
) {

    private var onClickMoreListener: ((T, View) -> Unit)? = null

    fun setOnClickMoreListener(onClick: (T, View) -> Unit) {
        onClickMoreListener = onClick
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return onCreateViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item = item, onClickMoreListener = onClickMoreListener)
    }

}


