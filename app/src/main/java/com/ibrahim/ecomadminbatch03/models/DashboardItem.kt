package com.ibrahim.ecomadminbatch03.models

import com.ibrahim.ecomadminbatch03.R

data class DashboardItem(
    val icon:Int,
    val title:String
)
val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(icon = R.drawable.add_icon, title = "Add Product"),
    DashboardItem(icon = R.drawable.view_icon, title = "View Product"),
    DashboardItem(icon = R.drawable.order_icon, title = "Order"),
    DashboardItem(icon = R.drawable.catagory_icon, title = "Category"),
    DashboardItem(icon = R.drawable.user_icon, title = "User")
)
