package com.suatzengin.forgotpassword.presentation.account_list.iban

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suatzengin.forgotpassword.base.BaseViewHolder
import com.suatzengin.forgotpassword.databinding.IbanListItemBinding
import com.suatzengin.forgotpassword.domain.model.Iban

class IbanViewHolder(private val binding: IbanListItemBinding) : BaseViewHolder<Iban>(binding.root) {
    override fun bind(item: Iban, onClickMoreListener: ((Iban, View) -> Unit)?) {
        binding.apply {
            tvOwner.text = item.owner
            tvBankName.text = item.bank
            tvIbanNumber.text = item.ibanNumber

            buttonMore.setOnClickListener { v: View ->
                onClickMoreListener?.invoke(item, v)
            }
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
