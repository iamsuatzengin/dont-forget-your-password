package com.suatzengin.forgotpassword.presentation.account_list

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suatzengin.forgotpassword.presentation.account_list.iban.IbanFragment
import com.suatzengin.forgotpassword.presentation.account_list.social.SocialAccountFragment

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { SocialAccountFragment() }
            1 -> { IbanFragment() }
            else -> { Fragment() }
        }
    }

}