package com.suatzengin.forgotpassword.presentation.account_list.iban

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.common.copyToClipboard
import com.suatzengin.forgotpassword.databinding.FragmentIbanBinding
import com.suatzengin.forgotpassword.domain.model.Iban
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IbanFragment : Fragment() {
    private var _binding: FragmentIbanBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: IbanRecyclerAdapter
    private val viewModel: IbanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIbanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = IbanRecyclerAdapter()

        setupRecyclerView()
        observe()

        recyclerAdapter.setOnClickMoreListener { iban, v ->
            showMenu(v, R.menu.menu_main, iban)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int, iban: Iban) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.option_edit -> {

                    true
                }

                R.id.option_copy -> {
                    val copiedText = iban.ibanNumber
                    copiedText.copyToClipboard(requireContext())
                    true
                }

                R.id.option_delete -> {
                    viewModel.deleteIban(iban = iban)
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvIban
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { list ->
                    recyclerAdapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}