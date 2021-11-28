package com.saleman.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.saleman.R
import com.saleman.model.sales.SalesPendingOrdersItem
import com.saleman.model.sales.ShopsItem
import com.saleman.model.sales.UncoveredShopsResponse
import com.saleman.preference.SessionData
import com.saleman.ui.adapter.SalesUncoveredShopsAdapter
import com.saleman.utils.MyProgressDialog
import de.footprinttech.wms.db.DataBaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.isNullOrEmpty
import kotlin.collections.set

class SalesUncoveredShopsFragment : BaseFragment() {

    private var rootView: View? = null
    private var rvOrderList: RecyclerView? = null
    private var orderListAdapter = SalesUncoveredShopsAdapter()
    private lateinit var pd: MyProgressDialog
    private lateinit var txtNoItemFound: TextView
    private var orders = ArrayList<ShopsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_uncovered_shops, container, false)
        rvOrderList = rootView?.findViewById(R.id.rvOrderList)
        txtNoItemFound = rootView?.findViewById(R.id.txtNoItemFound)!!
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pd = MyProgressDialog(requireContext(), R.drawable.icons8_loader)
        hideButtomBar()
        setTitle("Uncovered Shops")
        hideBack()
        showMenu()

        mainActivity.edtSearch.setText("")
        mainActivity.edtSearch.clearFocus()
        rvOrderList?.adapter = orderListAdapter
        mainActivity.imgSearchText.setOnClickListener {
            val search = mainActivity.edtSearch.text.trim()
//            if (!TextUtils.isEmpty(search)) {
//                val newList = orders.filter { order -> order.shop?.name?.contains(search)!! || order.order_no.contains(search) }
//                if (!newList.isNullOrEmpty()) {
//                    rvOrderList?.visibility = View.VISIBLE
//                    txtNoItemFound.visibility = View.GONE
//                    orderListAdapter.setData(newList, {
//                        mainActivity.navController.navigate(R.id.orderDetailsFragment)
//                    })
//                } else {
//                    rvOrderList?.visibility = View.GONE
//                    txtNoItemFound.visibility = View.VISIBLE
//                }
//                mainActivity.edtSearch.clearFocus()
//            } else {
//                rvOrderList?.visibility = View.VISIBLE
//                txtNoItemFound.visibility = View.GONE
//                orderListAdapter.setData(orders, {
////                    mainActivity.navController.navigate(R.id.orderDetailsFragment)
//                    val order = orders.get(it)
////                    codeForeShare("Order no: " + order.order_no)
//                })
//                mainActivity.edtSearch.clearFocus()
//            }
        }
        apiCallingForOrders()
    }

    private fun codeForeShare(share: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Order")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, share)
        mainActivity.startActivity(sharingIntent)
    }


    fun apiCallingForOrders() {
        pd.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            val user = DataBaseHelper.getDatabaseDao(requireContext())
                .getUser(SessionData.getInstance(requireContext()).getUserId()!!.toLong())

            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            params["distributor_id"] = SessionData.getInstance(requireContext()).getDistrubuterId()!!
            params["user_id"] = user.id.toString()
            try {
                val responseOfOrderResponse = apiCallingRequest.salesUncoveredShops(params)
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    if (!responseOfOrderResponse.shops.isNullOrEmpty()) {
                        orders = responseOfOrderResponse.shops as ArrayList<ShopsItem>
                        if (!orders.isNullOrEmpty()) {
                            rvOrderList?.visibility = View.VISIBLE
                            txtNoItemFound.visibility = View.GONE
                            orderListAdapter.setData(orders, {
                                val order = orders.get(it)
//                                codeForeShare("Order No: " + order.order_no)
                            })
                        } else {
                            rvOrderList?.visibility = View.GONE
                            txtNoItemFound.visibility = View.VISIBLE
                        }
                    } else {
                        rvOrderList?.visibility = View.GONE
                        txtNoItemFound.visibility = View.VISIBLE
                    }
                }
            } catch (apiEx: IOException) {
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    rvOrderList?.visibility = View.GONE
                    txtNoItemFound.visibility = View.VISIBLE
                }
            }
        }

    }

}