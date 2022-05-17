package com.ibrahim.ecomadminbatch03.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.ibrahim.ecomadminbatch03.utils.collectionCategory

class ProductRepository {
    private val db = FirebaseFirestore.getInstance()
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