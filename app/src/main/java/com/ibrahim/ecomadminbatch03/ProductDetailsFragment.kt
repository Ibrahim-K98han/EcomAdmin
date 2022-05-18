package com.ibrahim.ecomadminbatch03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ibrahim.ecomadminbatch03.databinding.FragmentProductDetailsBinding
import com.ibrahim.ecomadminbatch03.databinding.FragmentViewProductBinding
import com.ibrahim.ecomadminbatch03.utils.getFormattedDate
import com.ibrahim.ecomadminbatch03.vidwmodels.ProductViewModel

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private var id:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        id = arguments?.getString("id")
        id?.let {
            productViewModel.getProductsById(it).observe(viewLifecycleOwner){
                binding.product = it
            }
        }
        id?.let {
            productViewModel.getPurchaseByProductId(it).observe(viewLifecycleOwner){
                var puarchaseHistory = ""
                it.forEach{
                    puarchaseHistory += "on ${getFormattedDate(it.purchaseDate!!.seconds * 1000, "dd/MM/yyyy")} - Qty ${it.quantity} - Price: ${it.purchasePrice}\n"
                }
                binding.purchaseHistoryTV.text = puarchaseHistory
            }
        }
        return binding.root
    }

}