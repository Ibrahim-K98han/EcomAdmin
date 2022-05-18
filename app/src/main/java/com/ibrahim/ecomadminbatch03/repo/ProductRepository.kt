package com.ibrahim.ecomadminbatch03.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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
    fun getAllProducts():LiveData<List<Product>>{
        val productLD = MutableLiveData<List<Product>>()
        db.collection(collectionProduct)
            .addSnapshotListener { value, error ->
                if (error != null){
                    return@addSnapshotListener
                }
                val tempList = mutableListOf<Product>()
                for(doc in value!!.documents){
                    doc.toObject(Product::class.java)?.let { tempList.add(it) }
                }
                productLD.value = tempList
            }
        return productLD
    }
    fun getProductsById(id:String):LiveData<Product>{
        val productLD = MutableLiveData<Product>()
        db.collection(collectionProduct).document(id)
            .addSnapshotListener { value, error ->
                if (error != null){
                    return@addSnapshotListener
                }

                productLD.value = value?.toObject(Product::class.java)
            }
        return productLD
    }

    fun getPurchaseByProductId(id:String):LiveData<List<Puarchase>>{
        val puarchaseLD = MutableLiveData<List<Puarchase>>()
        db.collection(collectionPurchase)
            .whereEqualTo("productId",id)
            .addSnapshotListener { value, error ->
                if (error != null){
                    return@addSnapshotListener
                }

                val tempList = mutableListOf<Puarchase>()
                for(doc in value!!.documents){
                    doc.toObject(Puarchase::class.java)?.let { tempList.add(it) }
                }
                puarchaseLD.value = tempList
            }
        return puarchaseLD
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