package com.ibrahim.ecomadminbatch03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ibrahim.ecomadminbatch03.adapters.DashboardAdapter
import com.ibrahim.ecomadminbatch03.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment() {

    private lateinit var binding:FragmentDashBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater,container,false)
        val adapter = DashboardAdapter()
        val glm = GridLayoutManager(requireActivity(),2)
        binding.dashBoardRV.layoutManager = glm
        binding.dashBoardRV.adapter = adapter
        return binding.root
    }

}