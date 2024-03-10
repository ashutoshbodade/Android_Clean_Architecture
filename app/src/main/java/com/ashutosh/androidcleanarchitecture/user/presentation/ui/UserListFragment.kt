package com.ashutosh.androidcleanarchitecture.user.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashutosh.androidcleanarchitecture.databinding.FragmentUserListBinding
import com.ashutosh.androidcleanarchitecture.user.presentation.adaptor.UserPagingAdaptor
import com.ashutosh.androidcleanarchitecture.user.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding:FragmentUserListBinding?=null
    private val binding get() = _binding!!
    val viewModel by activityViewModels<UserViewModel>()

    private val userAdapter = UserPagingAdaptor()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdaptor()
        bindFlowData()
    }

    private fun setupAdaptor() {
       binding.apply {
           rvUsers.adapter = userAdapter
           rvUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
       }
    }

    private fun bindFlowData() {
        lifecycleScope.launch {
            viewModel.getUserList().collectLatest {
                userAdapter.submitData(it)
            }
        }
        userAdapter.addLoadStateListener { loadState ->
            if(loadState.mediator?.refresh is LoadState.Loading){
                binding.progress.isVisible = true
            }else{
                binding.progress.isVisible = false
                val error = when {
                    loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                    loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                    loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    Toast.makeText(requireContext(), it.error.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}