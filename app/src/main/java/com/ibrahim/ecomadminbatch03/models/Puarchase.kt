package com.ibrahim.ecomadminbatch03.models

import java.sql.Timestamp

data class Puarchase(
    var productId: String? = null,
    var purchaseId: String? = null,
    var purchaseDate: Timestamp? = null,
    var quantity: Double = 0.0,
    var productPrice: Double = 1.0,
)
