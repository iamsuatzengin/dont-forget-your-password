package com.suatzengin.forgotpassword.presentation.account_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.databinding.FragmentAccountListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = "Social Accounts"
                }
                1 -> {
                    tab.text = "IBANs"
                }
            }
        }.attach()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_addAccountFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}