package com.ibrahim.ecomadminbatch03.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.ecomadminbatch03.databinding.DashboardItemBinding
import com.ibrahim.ecomadminbatch03.models.DashboardItem
import com.ibrahim.ecomadminbatch03.models.DashboardItemType
import com.ibrahim.ecomadminbatch03.models.dashboardItemList

class DashboardAdapter(val callback:(DashboardItemType)->Unit):RecyclerView.Adapter<DashboardAdapter.DashboardItemViewHolder>() {

    class DashboardItemViewHolder(val binding:DashboardItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:DashboardItem){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val binding = DashboardItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return DashboardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardItemViewHolder, position: Int) {
        val item = dashboardItemList.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback(item.type)
        }
    }

    override fun getItemCount() = dashboardItemList.size
}