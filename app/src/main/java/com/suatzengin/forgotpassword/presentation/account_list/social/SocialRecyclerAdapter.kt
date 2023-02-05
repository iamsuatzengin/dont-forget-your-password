package com.suatzengin.forgotpassword.presentation.account_list.social

import android.view.ViewGroup
import com.suatzengin.forgotpassword.base.BaseAdapter
import com.suatzengin.forgotpassword.base.BaseViewHolder
import com.suatzengin.forgotpassword.domain.model.SocialAccount

class SocialRecyclerAdapter : BaseAdapter<SocialAccount>(
    areItemsTheSame = {oldItem, newItem -> oldItem.id == newItem.id},
    areContentsTheSame = {oldItem, newItem -> oldItem == newItem}
) {
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SocialAccount> {
        return SocialViewHolder.from(parent = parent)
    }
}