package com.saleman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.sales.SalesInventoryResponseItem

class InventoryAdapter : RecyclerView.Adapter<InventoryAdapter.PlanningViewHolder>() {

    var searchList: List<SalesInventoryResponseItem>? = null
    var myCallback: ((Int) -> Unit?)? = null

    fun setData(
            searchList: List<SalesInventoryResponseItem>,
            myCallback: (pos: Int) -> Unit
    ) {
        this.searchList = searchList
        this.myCallback = myCallback
        notifyDataSetChanged()
    }

    class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtProName = itemView.findViewById<TextView>(R.id.txtProName)
        val txtProType= itemView.findViewById<TextView>(R.id.txtProType)
        val txtProCategory= itemView.findViewById<TextView>(R.id.txtProCategory)
        val txtProCompany= itemView.findViewById<TextView>(R.id.txtProCompany)
        val txt_mrp= itemView.findViewById<TextView>(R.id.txt_mrp)
        val txtBatchExpire= itemView.findViewById<TextView>(R.id.txtBatchExpire)
        val txtExpireDate= itemView.findViewById<TextView>(R.id.txtShelfLife)
        val txtCurrentStock= itemView.findViewById<TextView>(R.id.txtCurrentStock)
        val txtDeliveredStockCurrentFuture= itemView.findViewById<TextView>(R.id.txtDeliveredStockCurrentFuture)
        val txtUndeliveredStock= itemView.findViewById<TextView>(R.id.txtUndeliveredStock)
        val txtDelieveredStock= itemView.findViewById<TextView>(R.id.txtDelieveredStock)
        val txtMiniQnty= itemView.findViewById<TextView>(R.id.txtMiniQnty)
        val txtRemainingStock= itemView.findViewById<TextView>(R.id.txtRemainingStock)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_browse, parent, false)
        return PlanningViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val searchItem = searchList?.get(position)
        val type = searchItem?.type
        val category = searchItem?.category
        val company = searchItem?.company

        var mrp = ""
        var mintQty = ""
        var currentStock = ""

        if(searchItem?.distPrice!=null){
            mrp = searchItem?.distPrice.toString()
        }

        if(searchItem?.minQty!=null){
            mintQty = searchItem?.minQty
        }

        if(searchItem?.inventory!=null){
            currentStock = searchItem?.inventory.toString()
        }
        var expDate = ""
        if(searchItem?.expValidity!=null){
            expDate = searchItem.expValidity+" "+searchItem.expValidityUnit!!
        }

        holder.txtProName.setText(searchItem?.name)
        holder.txtProType.setText(type?.name)
        holder.txtProCategory.setText(category?.name)
        holder.txtProCompany.setText(company?.name)
        holder.txtExpireDate.setText(expDate)
        holder.txtCurrentStock.setText(currentStock)
        holder.txtMiniQnty.setText(mintQty)
        holder.txt_mrp.setText(mrp)




//        holder.txtAddCart.setOnClickListener {
//            myCallback?.invoke(position)
//        }
    }

    override fun getItemCount(): Int {
        if (searchList == null) {
            return 0
        } else {
            return searchList!!.size
        }
    }

}