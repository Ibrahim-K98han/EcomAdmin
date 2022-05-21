package com.ibrahim.ecomadminbatch03.models

import com.google.firebase.Timestamp
import com.ibrahim.ecomadminbatch03.utils.OrderStatus
import com.ibrahim.ecomadminbatch03.utils.PaymentMethod

data class Order(
    var orderId:String? = null,
    var userId:String? = null,
    var orderDate:Timestamp? = null,
    var deliveryCharge:Int = 0,
    var discount:Int = 0,
    var vat:Int = 0,
    var orderStatus:String = OrderStatus.pending,
    var paymentMethod: String = PaymentMethod.cod,
    var deliveryAddress:String? = null,
)
