package com.saleman.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.ApiException
import com.saleman.R
import com.saleman.model.products.Company
import com.saleman.model.products.ProductType
import com.saleman.model.products.Products
import com.saleman.model.sales.OrdersItem
import com.saleman.preference.SessionData
import com.saleman.ui.adapter.PaymentAdapter
import com.saleman.utils.MyProgressDialog
import com.saleman.utils.PopupUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.filter
import kotlin.collections.set

class PaymentFragment : BaseFragment() {

    private lateinit var rvBrowse: RecyclerView
    private lateinit var txtNoFound: TextView
    var browseAdapter = PaymentAdapter()
    var listOfItem = ArrayList<Products>()
    var TAG = "@@@MainActivity"
    private lateinit var pd: MyProgressDialog
    var selectedShelfStr=""
    var selectedProductTypeStr=""
    var selectedCompanyStr=""
    var mostOrderedProductBl=false
    var selPriceRangeMin=""
    var selPriceRangeMax=""
    var selPriceMarginMin=""
    var selPriceMarginMax=""
    lateinit var productTypeList: List<OrdersItem>
    lateinit var companyList:List<Company>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_payment, container, false)
        rvBrowse = root.findViewById(R.id.rvBrowse)
        txtNoFound = root.findViewById(R.id.txtNoFound)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pd = MyProgressDialog(requireContext(), R.drawable.icons8_loader)
        setTitle("Pending Payments")
        showMenu()
        hideBack()
        showButtom()
        hideFilter()
        mainActivity.edtSearch.setText("")
        mainActivity.edtSearch.clearFocus()
        rvBrowse.adapter = browseAdapter
        mainActivity.imgSearchText.setOnClickListener {
            val search = mainActivity.edtSearch.text.trim()
            if (!TextUtils.isEmpty(search)) {
                val newList = listOfItem.filter { item -> item.name.contains(search) }
                mainActivity.edtSearch.clearFocus()
            } else {
                mainActivity.edtSearch.clearFocus()
            }
        }
        mainActivity.rlFilter.setOnClickListener {
//            PopupUtils.fillterDialog(
//                selectedShelfStr,
//                selectedProductTypeStr,
//                selectedCompanyStr,
//                mostOrderedProductBl,
//                selPriceRangeMin,
//                selPriceRangeMax,
//                selPriceMarginMin,
//                selPriceMarginMax,
//                productTypeList,
//                companyList,
//                requireActivity(), {
//                    selectedShelfStr = it.shelfLife!!
//                    selectedProductTypeStr= it.productType!!
//                    selectedCompanyStr= it.company!!
//                    mostOrderedProductBl= it.mostOrderedProduct!!
//                    selPriceRangeMin= it.priceRangeMin!!
//                    selPriceRangeMax= it.priceRangeMax!!
//                    selPriceMarginMin= it.priceMarginMin!!
//                    selPriceMarginMax= it.priceMarginMax!!
//                    Log.v(TAG,"Filter  Values : shelfLife ${it.shelfLife},priceMarginMin${it.priceMarginMin}" )
//            }
//            )
        }
        getSalesPendingPayment()
    }
    public fun getSalesPendingPayment() {
        pd.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            params["distributor_id"] = SessionData.getInstance(requireContext()).getDistrubuterId()!!
            try {
                val responseOfCity = apiCallingRequest.getSalesPendingPayment(params)
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    productTypeList = responseOfCity.orders as java.util.ArrayList<OrdersItem>
                    browseAdapter.setData(productTypeList,{

                    })
                    if(productTypeList!=null && productTypeList.size>0){
                        rvBrowse.visibility = View.VISIBLE
                        txtNoFound.visibility = View.GONE
                    }else{
                        rvBrowse.visibility = View.GONE
                        txtNoFound.visibility = View.VISIBLE
                    }
                }
            } catch (apiEx: ApiException) {
                apiEx.printStackTrace()
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    Toast.makeText(requireContext(),resources.getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}