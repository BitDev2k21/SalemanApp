package com.saleman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.products.Products

class BrowseAdapter : RecyclerView.Adapter<BrowseAdapter.PlanningViewHolder>() {

    var searchList: List<Products>? = null
    var myCallback: ((Int) -> Unit?)? = null

    fun setData(
            searchList: List<Products>,
            myCallback: (pos: Int) -> Unit
    ) {
        this.searchList = searchList
        this.myCallback = myCallback
        notifyDataSetChanged()
    }

    class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtProName = itemView.findViewById<TextView>(R.id.txtProName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_browse, parent, false)
        return PlanningViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val searchItem = searchList?.get(position)
        holder.txtProName.setText(searchItem?.name)
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