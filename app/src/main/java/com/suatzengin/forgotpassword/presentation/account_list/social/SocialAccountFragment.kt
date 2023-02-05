package com.suatzengin.forgotpassword.presentation.account_list.social

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
import com.suatzengin.forgotpassword.databinding.FragmentSocialAccountBinding
import com.suatzengin.forgotpassword.domain.model.SocialAccount
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SocialAccountFragment : Fragment() {
    private var _binding: FragmentSocialAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerAdapter: SocialRecyclerAdapter
    private val viewModel: SocialAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSocialAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = SocialRecyclerAdapter()

        setupRecyclerView()
        observeData()

        recyclerAdapter.setOnClickMoreListener { social, v ->
            //Toast.makeText(requireContext(), it.usernameOrEmail, Toast.LENGTH_SHORT).show()
            showMenu(v, R.menu.menu_main, social)
        }
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

    private fun setupRecyclerView() {
        val recyclerView = binding.rvSocialAccount
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
    }

    private fun observeData() {
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