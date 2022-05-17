package com.ibrahim.ecomadminbatch03.vidwmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.ecomadminbatch03.models.Product
import com.ibrahim.ecomadminbatch03.models.Puarchase
import com.ibrahim.ecomadminbatch03.repo.ProductRepository
import java.util.*

class ProductViewModel:ViewModel() {
    val repository = ProductRepository()
    val productListLD: MutableLiveData<List<Product>> = MutableLiveData()
    val purchaseListLD: MutableLiveData<List<Puarchase>> = MutableLiveData()

    fun getAllCategories() : LiveData<List<String>> {
        return repository.getAllCategory()
    }
}