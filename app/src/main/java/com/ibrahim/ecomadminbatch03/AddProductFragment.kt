package com.ibrahim.ecomadminbatch03

import android.R
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.firebase.Timestamp
import com.ibrahim.ecomadminbatch03.customdialogs.DatePickerFragment
import com.ibrahim.ecomadminbatch03.databinding.FragmentAddProductBinding
import com.ibrahim.ecomadminbatch03.utils.getFormattedDate
import com.ibrahim.ecomadminbatch03.vidwmodels.ProductViewModel


class AddProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by activityViewModels()
    private lateinit var binding: FragmentAddProductBinding
    private var category: String? = null
    private var timestamp:Timestamp? = null
    private var imageUrl:String? = null
    private var bitmap:Bitmap?=null

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
        binding.dateBtn.setOnClickListener {
            DatePickerFragment{
                timestamp = it
                binding.dateBtn.text = getFormattedDate(timestamp!!.seconds*1000,"dd/MM/yyyy")
            }.show(childFragmentManager,null)
        }

        binding.captureBtn.setOnClickListener {
            dispatchTakePictureIntent()
        }

        return binding.root
    }

    val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            bitmap = it.data?.extras?.get("data") as Bitmap
            binding.productImageView.setImageBitmap(bitmap)
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            resultLauncher.launch(takePictureIntent)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

}