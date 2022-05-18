package com.ibrahim.ecomadminbatch03.models

import java.sql.Timestamp

data class Puarchase(
    var productId: String? = null,
    var purchaseId: String? = null,
    var purchaseDate: com.google.firebase.Timestamp? = null,
    var quantity: Double = 0.0,
    var purchasePrice: Double = 1.0,
)
