package com.suatzengin.forgotpassword.presentation.account_list.iban

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.forgotpassword.databinding.IbanListItemBinding
import com.suatzengin.forgotpassword.domain.model.Iban

class IbanRecyclerAdapter : ListAdapter<Iban, IbanViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IbanViewHolder {
        return IbanViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: IbanViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Iban>() {
        override fun areItemsTheSame(oldItem: Iban, newItem: Iban): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Iban, newItem: Iban): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class IbanViewHolder(private val binding: IbanListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Iban) {
        binding.apply {
            tvOwner.text = item.owner
            tvBankName.text = item.bank
            tvIbanNumber.text = item.ibanNumber
        }
    }


    companion object {
        fun from(parent: ViewGroup): IbanViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = IbanListItemBinding.inflate(layoutInflater, parent, false)
            return IbanViewHolder(binding)
        }
    }
}
