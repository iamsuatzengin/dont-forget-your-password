package com.suatzengin.forgotpassword.presentation.account_list.iban

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.base.BaseFragment
import com.suatzengin.forgotpassword.common.copyToClipboard
import com.suatzengin.forgotpassword.databinding.FragmentIbanBinding
import com.suatzengin.forgotpassword.domain.model.Iban
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class IbanFragment : BaseFragment<FragmentIbanBinding, IbanViewModel>() {

    private lateinit var recyclerAdapter: IbanRecyclerAdapter
    override val viewModel: IbanViewModel by viewModels()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIbanBinding {
        return FragmentIbanBinding.inflate(inflater, container, false)
    }

    override val observeDataBlock: suspend CoroutineScope.() -> Unit = {
        viewModel.state.collect { list ->
            recyclerAdapter.submitList(list)
        }
    }

    override fun onViewCreated() {
        recyclerAdapter = IbanRecyclerAdapter()
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

    override fun setupRecyclerView() {
        val recyclerView = binding.rvIban
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
    }
}