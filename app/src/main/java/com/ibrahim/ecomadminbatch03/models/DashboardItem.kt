package com.ibrahim.ecomadminbatch03.models

import com.ibrahim.ecomadminbatch03.R

data class DashboardItem(
    val icon:Int,
    val title:String,
    val type:DashboardItemType
)

enum class DashboardItemType{
    ADD_PRODUCT,VIEW_PRODUCT,ORDER,CATEGORY,USER,REPORT,SETTINGS
}

val dashboardItemList = listOf<DashboardItem>(
    DashboardItem(icon = R.drawable.add_icon, title = "Add Product",type=DashboardItemType.ADD_PRODUCT),
    DashboardItem(icon = R.drawable.view_icon, title = "View Product",type=DashboardItemType.VIEW_PRODUCT),
    DashboardItem(icon = R.drawable.order_icon, title = "Order",type=DashboardItemType.ORDER),
    DashboardItem(icon = R.drawable.catagory_icon, title = "Category",type=DashboardItemType.CATEGORY),
    DashboardItem(icon = R.drawable.user_icon, title = "User",type=DashboardItemType.USER),
    DashboardItem(icon = R.drawable.report_icon, title = "Report",type=DashboardItemType.REPORT),
    DashboardItem(icon = R.drawable.setting, title = "Settings",type=DashboardItemType.SETTINGS)
)
