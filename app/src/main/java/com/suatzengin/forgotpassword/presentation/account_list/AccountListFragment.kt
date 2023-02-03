package com.suatzengin.forgotpassword.presentation.account_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.databinding.FragmentAccountListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_addAccountFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}