package com.ibrahim.ecomadminbatch03.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahim.ecomadminbatch03.models.Product
import com.ibrahim.ecomadminbatch03.models.Puarchase
import com.ibrahim.ecomadminbatch03.utils.collectionCategory
import com.ibrahim.ecomadminbatch03.utils.collectionProduct
import com.ibrahim.ecomadminbatch03.utils.collectionPurchase

class ProductRepository {
    private val db = FirebaseFirestore.getInstance()
    fun addNewProduct(product: Product,puarchase: Puarchase,callback:(String)->Unit){
        val wb = db.batch()
        val productDoc = db.collection(collectionProduct).document()
        val puarchaseDoc = db.collection(collectionPurchase).document()
        product.id = productDoc.id
        puarchase.purchaseId = puarchaseDoc.id
        puarchase.productId = product.id
        wb.set(productDoc, product)
        wb.set(puarchaseDoc, puarchase)
        wb.commit().addOnSuccessListener {
            callback("Success")
        }.addOnFailureListener {
            callback("Failure")
        }
    }
    fun getAllCategory():LiveData<List<String>>{
        val catLD = MutableLiveData<List<String>>()
        db.collection(collectionCategory)
            .addSnapshotListener { value, error ->
                if(error !=null){
                    return@addSnapshotListener
                }

                val tempList = mutableListOf<String>()
                for(doc in value!!.documents){
                    tempList.add(doc.get("name").toString())
                }
                catLD.value = tempList
            }
        return catLD
    }

}