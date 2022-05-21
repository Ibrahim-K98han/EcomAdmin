package com.ibrahim.ecomadminbatch03.utils

const val TAG = "MadBatch03"
const val collectionAdmin = "Admins"
const val collectionProduct = "Product"
const val collectionPurchase = "Purchase"
const val collectionCategory = "Category"
const val collectionUser = "User"
const val collectionOrder= "Order"
const val collectionOrderDetails= "Order Details"
const val collectionOrderConstants = "Order Constants"
const val collectionOrderSettings = "OrderSettings"
const val documentOrderConstants = "OrderConstants"

class PaymentMethod{
    companion object{
        const val cod = "Cash on Delivery"
        const val online = "Online"
    }
}

class OrderStatus{
    companion object{
        const val pending = "Pending"
        const val delivered = "Delivered"
        const val cancelled = "Cancelled"
    }
}