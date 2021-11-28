package com.saleman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.sales.ShopsItem


class SalesUncoveredShopsAdapter : RecyclerView.Adapter<SalesUncoveredShopsAdapter.PlanningViewHolder>() {

    var searchList: List<ShopsItem>? = null
    var myCallback: ((Int) -> Unit?)? = null

    fun setData(
            searchList: List<ShopsItem>,
            myCallback: (pos: Int) -> Unit
    ) {
        this.searchList = searchList
        this.myCallback = myCallback
        notifyDataSetChanged()
    }

    class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtShopName = itemView.findViewById<TextView>(R.id.txtShopName)
        val txtLocation = itemView.findViewById<TextView>(R.id.txtLocation)
        val txtAddress = itemView.findViewById<TextView>(R.id.txtAddress)
        val textOwner = itemView.findViewById<TextView>(R.id.txtOwner)
        val txtContact = itemView.findViewById<TextView>(R.id.txtContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_uncovered_sohps_list, parent, false)
        return PlanningViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val searchItem = searchList?.get(position)
        val location = searchItem?.location
        holder.txtShopName.text = searchItem?.name
        holder.txtLocation.text = location?.name
        holder.txtAddress.text = searchItem?.address
        holder.txtContact.text = searchItem?.contact
        holder.textOwner.text = searchItem?.ownerName
    }


    override fun getItemCount(): Int {
        if (searchList == null) {
            return 0
        } else {
            return searchList!!.size
        }
    }

}