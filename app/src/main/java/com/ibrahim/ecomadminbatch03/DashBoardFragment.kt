package com.ibrahim.ecomadminbatch03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ibrahim.ecomadminbatch03.adapters.DashboardAdapter
import com.ibrahim.ecomadminbatch03.databinding.FragmentDashBoardBinding
import com.ibrahim.ecomadminbatch03.models.DashboardItemType
import com.ibrahim.ecomadminbatch03.vidwmodels.LoginViewModel

class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        loginViewModel.authStateLD.observe(viewLifecycleOwner) {
            if (it == LoginViewModel.AuthState.UNAUTHENTICATED) {
                findNavController().navigate(R.id.action_dashBoardFragment_to_loginFragment)
            }
        }
        val adapter = DashboardAdapter{
            navigateToDashboardItemPage(it)
        }
        val glm = GridLayoutManager(requireActivity(), 2)
        binding.dashBoardRV.layoutManager = glm
        binding.dashBoardRV.adapter = adapter
        return binding.root
    }

    private fun navigateToDashboardItemPage(it: DashboardItemType) {
        when(it) {
            DashboardItemType.ADD_PRODUCT -> findNavController().navigate(R.id.action_dashBoardFragment_to_addProductFragment2)
            DashboardItemType.VIEW_PRODUCT -> findNavController().navigate(R.id.action_dashBoardFragment_to_viewProductFragment2)
            DashboardItemType.CATEGORY -> findNavController().navigate(R.id.action_dashBoardFragment_to_categoryFragment2)
        }
    }

}