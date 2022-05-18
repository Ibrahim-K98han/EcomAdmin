package com.ibrahim.ecomadminbatch03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.ecomadminbatch03.adapters.ProductAdapter
import com.ibrahim.ecomadminbatch03.databinding.FragmentViewProductBinding
import com.ibrahim.ecomadminbatch03.vidwmodels.ProductViewModel

class ViewProductFragment : Fragment() {

    private lateinit var binding:FragmentViewProductBinding
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewProductBinding.inflate(inflater,container,false)
        val adapter = ProductAdapter()
        binding.productRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.productRV.adapter = adapter
        productViewModel.getProducts().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        return binding.root
    }

}