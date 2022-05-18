package com.ibrahim.ecomadminbatch03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.google.firebase.Timestamp
import com.ibrahim.ecomadminbatch03.customdialogs.DatePickerFragment
import com.ibrahim.ecomadminbatch03.databinding.FragmentProductDetailsBinding
import com.ibrahim.ecomadminbatch03.databinding.FragmentViewProductBinding
import com.ibrahim.ecomadminbatch03.databinding.RepurchaseLayoutBinding
import com.ibrahim.ecomadminbatch03.models.Puarchase
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
        binding.repurchaseBtn.setOnClickListener {
            showRepurchaseDialog()
        }
        return binding.root
    }

    private fun showRepurchaseDialog() {
        var purchaseTime: Timestamp? = null
        val builder = AlertDialog.Builder(requireActivity()).apply {
            setTitle("Re-Purchase Product")
            val rlbinding = RepurchaseLayoutBinding.inflate(LayoutInflater.from(requireContext()))
            setView(rlbinding.root)
            rlbinding.repurchaseDateBtn.setOnClickListener {
                DatePickerFragment{
                    purchaseTime = it
                    rlbinding.repurchaseDateBtn.text = getFormattedDate(it.seconds * 1000, "dd/MM/yyyy")
                }.show(childFragmentManager, null)
            }
            setPositiveButton("Buy") { dilaog, value ->
                val qty = rlbinding.repurchaseQtyET.text.toString()
                val price = rlbinding.repurchasePriceET.text.toString()
                // TODO: validate
                val purchase = Puarchase(
                    productId = id,
                    purchaseDate = purchaseTime,
                    purchasePrice = price.toDouble(),
                    quantity = qty.toDouble()
                )
                productViewModel.addRepurchase(purchase)
            }
            setNegativeButton("Close", null)
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}