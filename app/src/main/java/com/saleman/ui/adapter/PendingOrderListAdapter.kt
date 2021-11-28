package com.saleman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.sales.OrderitemsItem
import com.saleman.model.sales.SalesPendingOrdersItem


class PendingOrderListAdapter : RecyclerView.Adapter<PendingOrderListAdapter.PlanningViewHolder>() {

    var searchList: List<SalesPendingOrdersItem>? = null
    var myCallback: ((Int) -> Unit?)? = null

    fun setData(
            searchList: List<SalesPendingOrdersItem>,
            myCallback: (pos: Int) -> Unit
    ) {
        this.searchList = searchList
        this.myCallback = myCallback
        notifyDataSetChanged()
    }

    class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rlItem = itemView.findViewById<RelativeLayout>(R.id.rlItem)
        val rvOrderItem = itemView.findViewById<RecyclerView>(R.id.rvOrderItem)
        val txtShopName = itemView.findViewById<TextView>(R.id.txtShopName)
        val txtOrderNo = itemView.findViewById<TextView>(R.id.txtOrderNo)
        val txtTotalPrice = itemView.findViewById<TextView>(R.id.txtTotalPrice)
        val rlShare = itemView.findViewById<RelativeLayout>(R.id.rlShare)
        val txtDate = itemView.findViewById<TextView>(R.id.txtDate)
        val order_status_accepted = itemView.findViewById<TextView>(R.id.order_status_accepted)
        val order_status_waiting = itemView.findViewById<TextView>(R.id.order_status_waiting)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order_list, parent, false)
        return PlanningViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val searchItem = searchList?.get(position)
        var status = searchItem?.status?.toInt()
        if(status !=null && status == 1){
            holder.order_status_accepted.visibility = View.VISIBLE
            holder.order_status_waiting.visibility = View.GONE
        }else{
            holder.order_status_accepted.visibility = View.GONE
            holder.order_status_waiting.visibility = View.VISIBLE
        }
        val orderListAdapter = OrderItemListAdapter()
        holder.txtShopName.text = searchItem?.shop?.name
        holder.txtOrderNo.text = searchItem?.orderNo
        holder.txtTotalPrice.text = "" + searchItem?.total
        holder.txtDate.text = "" + searchItem?.createdAt?.substringBeforeLast(" ")
        holder.rvOrderItem.adapter = orderListAdapter
        orderListAdapter.setData(searchItem?.orderitems as List<OrderitemsItem>)
        holder.rlItem.setOnClickListener {
        }
        holder.rlShare.setOnClickListener {
            myCallback?.invoke(position)
        }
    }


    override fun getItemCount(): Int {
        if (searchList == null) {
            return 0
        } else {
            return searchList!!.size
        }
    }

}