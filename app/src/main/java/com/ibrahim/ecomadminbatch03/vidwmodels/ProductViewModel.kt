package com.ibrahim.ecomadminbatch03.vidwmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.ibrahim.ecomadminbatch03.models.Product
import com.ibrahim.ecomadminbatch03.models.Puarchase
import com.ibrahim.ecomadminbatch03.repo.ProductRepository
import java.io.ByteArrayOutputStream
import java.util.*

class ProductViewModel:ViewModel() {
    val repository = ProductRepository()
    val productListLD: MutableLiveData<List<Product>> = MutableLiveData()
    val purchaseListLD: MutableLiveData<List<Puarchase>> = MutableLiveData()
    val errMsgLD = MutableLiveData<String>()
    val statusLD = MutableLiveData<String>()

    fun getAllCategories() : LiveData<List<String>> {
        return repository.getAllCategory()
    }

    fun uploadImage(bitmap: Bitmap, callback: (String) -> Unit) {
        val photoRef = FirebaseStorage.getInstance().reference
            .child("images/${System.currentTimeMillis()}")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val data: ByteArray = baos.toByteArray()
        val uploadTask = photoRef.putBytes(data)
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                callback(downloadUri)
            } else {
                errMsgLD.value = "could not save, please check your internet connection"
            }
        }
    }
    fun addNewProduct(product: Product,puarchase: Puarchase,callback:(String)->Unit){
        repository.addNewProduct(product,puarchase,callback)
    }

    fun addRepurchase(purchase: Puarchase) = repository.addRePurchase(purchase)

    fun getProducts() = repository.getAllProducts()
    fun getProductsById(id:String) = repository.getProductsById(id)
    fun getPurchaseByProductId(id:String) = repository.getPurchaseByProductId(id)
}