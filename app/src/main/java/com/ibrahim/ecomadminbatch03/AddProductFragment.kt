package com.ibrahim.ecomadminbatch03

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.ibrahim.ecomadminbatch03.databinding.FragmentAddProductBinding
import com.ibrahim.ecomadminbatch03.vidwmodels.ProductViewModel


class AddProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by activityViewModels()
    private lateinit var binding: FragmentAddProductBinding
    private var category: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        productViewModel.getAllCategories().observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                val spAdapter = ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner_dropdown_item, it)
                binding.catSP.adapter = spAdapter
            }
        }

        binding.catSP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = parent!!.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return binding.root
    }


}