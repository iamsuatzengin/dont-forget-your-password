package com.suatzengin.forgotpassword.presentation.account_list.social

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suatzengin.forgotpassword.R
import com.suatzengin.forgotpassword.base.BaseFragment
import com.suatzengin.forgotpassword.common.copyToClipboard
import com.suatzengin.forgotpassword.databinding.FragmentSocialAccountBinding
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class SocialAccountFragment : BaseFragment<FragmentSocialAccountBinding, SocialAccountViewModel>() {

    private lateinit var recyclerAdapter: SocialRecyclerAdapter
    override val viewModel: SocialAccountViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSocialAccountBinding {
        return FragmentSocialAccountBinding.inflate(inflater, container, false)
    }

    override val observeDataBlock: suspend CoroutineScope.() -> Unit = {
        viewModel.state.collect { list ->
            recyclerAdapter.submitList(list)
        }
    }

    override fun onViewCreated() {
        recyclerAdapter = SocialRecyclerAdapter()

        recyclerAdapter.setOnClickMoreListener { social, v ->
            showMenu(v, R.menu.menu_main, social)
        }
    }

    override fun setupRecyclerView() {
        val recyclerView = binding.rvSocialAccount
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int, socialAccount: SocialAccount) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.option_edit -> {

                    true
                }

                R.id.option_copy -> {
                    val copiedText = """
                        ${socialAccount.usernameOrEmail}
                        ${socialAccount.password}
                    """.trimIndent()
                    copiedText.copyToClipboard(requireContext())
                    Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.option_delete -> {
                    viewModel.deleteSocialAccount(socialAccount = socialAccount)
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

}