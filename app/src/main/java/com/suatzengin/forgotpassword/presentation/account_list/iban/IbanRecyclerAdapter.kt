package com.suatzengin.forgotpassword.presentation.account_list.iban

import android.view.ViewGroup
import com.suatzengin.forgotpassword.base.BaseAdapter
import com.suatzengin.forgotpassword.base.BaseViewHolder
import com.suatzengin.forgotpassword.domain.model.Iban

class IbanRecyclerAdapter : BaseAdapter<Iban>(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
) {
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<Iban> {
        return IbanViewHolder.from(parent = parent)
    }
}
