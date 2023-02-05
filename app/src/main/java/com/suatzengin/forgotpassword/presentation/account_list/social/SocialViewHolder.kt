package com.suatzengin.forgotpassword.presentation.account_list.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suatzengin.forgotpassword.base.BaseViewHolder
import com.suatzengin.forgotpassword.common.setPlatformIcon
import com.suatzengin.forgotpassword.databinding.SocialListItemBinding
import com.suatzengin.forgotpassword.domain.model.SocialAccount

class SocialViewHolder(
    private val binding: SocialListItemBinding
) : BaseViewHolder<SocialAccount>(binding.root) {
    override fun bind(item: SocialAccount, onClickMoreListener: ((SocialAccount, View) -> Unit)?) {
        binding.apply {
            tvPlatform.text = item.platform.name
            tvUsernameOrEmail.text = item.usernameOrEmail
            tvPassword.text = item.password
            ivPlatform.setPlatformIcon(item.platform)

            buttonMore.setOnClickListener{view: View ->
                onClickMoreListener?.invoke(item, view)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): SocialViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SocialListItemBinding.inflate(layoutInflater, parent, false)
            return SocialViewHolder(binding)
        }
    }
}