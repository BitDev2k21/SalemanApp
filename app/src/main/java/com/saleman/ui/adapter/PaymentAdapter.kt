package com.saleman.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.sales.OrdersItem
import com.saleman.utils.DateUtils
import com.saleman.utils.PopupUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.PlanningViewHolder>() {

    var searchList: List<OrdersItem>? = null
    var myCallback: ((Int) -> Unit?)? = null

    fun setData(
            searchList: List<OrdersItem>,
            myCallback: (pos: Int) -> Unit
    ) {
        this.searchList = searchList
        this.myCallback = myCallback
        notifyDataSetChanged()
    }

    class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtOrderNo = itemView.findViewById<TextView>(R.id.txtOrderNo)
        val txtStoreName = itemView.findViewById<TextView>(R.id.txtStoreName)
        val txtOrderPlaced = itemView.findViewById<TextView>(R.id.txtOrderPlaced)
        val txtOrderDelivered = itemView.findViewById<TextView>(R.id.txtOrderDelivered)
        val txtEndDatePayment = itemView.findViewById<TextView>(R.id.txtEndDatePayment)
        val txtAmount = itemView.findViewById<TextView>(R.id.txtAmount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false)
        return PlanningViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val searchItem = searchList?.get(position)
        val shop = searchItem?.shop
        val dist_details = searchItem?.distributorDetails
        val createdAt = searchItem?.createdAt
        val credit_period = dist_details?.creditPeriod
        var createdDate = DateUtils.convertToDate(createdAt.toString())
        if(!createdAt.isNullOrBlank()){
            createdDate = DateUtils.convertToDate(createdAt.toString())
        }
        var endDate = ""

        if(!createdDate.isNullOrBlank()){
            endDate = credit_period?.let { DateUtils.getEndDateOFPayment(createdDate, it) }.toString()
        }

        holder.txtStoreName.setText(shop?.name)
        holder.txtAmount.setText(searchItem?.total.toString())
        holder.txtOrderPlaced.setText(createdDate)
        holder.txtOrderNo.setText("Order No: "+searchItem?.id.toString())
        holder.txtOrderDelivered.setText(searchItem?.delivered_at?.toString())
        holder.txtEndDatePayment.setText(endDate)


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

    private fun getEndDateOfPayments(crateDate:String, creditPeriod: Int?):String{
        var retDate = ""
        try {
            var date: Date? = null
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            date = formatter.parse(crateDate)
            Log.v("@@@", "formated date  "+date.toString() + "")
            val c: Calendar = Calendar.getInstance()
            c.setTime(date)
            if (creditPeriod != null) {
                c.add(Calendar.DATE, creditPeriod)
            }
            date = c.getTime()
            retDate =date.toString()
            Log.v("@@@", "formated date  after credit "+date.toString() + "")
        }catch (e:Exception){
            e.printStackTrace()
        }
        return retDate
    }

}