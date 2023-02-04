package com.suatzengin.forgotpassword.presentation.account_list.social

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.forgotpassword.common.setPlatformIcon
import com.suatzengin.forgotpassword.databinding.SocialListItemBinding
import com.suatzengin.forgotpassword.domain.model.SocialAccount

class SocialRecyclerAdapter : ListAdapter<SocialAccount, SocialViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        return SocialViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<SocialAccount>() {
        override fun areItemsTheSame(oldItem: SocialAccount, newItem: SocialAccount): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SocialAccount, newItem: SocialAccount): Boolean {
            return oldItem.id == newItem.id
        }
    }
}


class SocialViewHolder(
    private val binding: SocialListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SocialAccount) {
        binding.apply {
            tvPlatform.text = item.platform.name
            tvUsernameOrEmail.text = item.usernameOrEmail
            tvPassword.text = item.password
            ivPlatform.setPlatformIcon(item.platform)
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