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
import com.saleman.model.sales.SalesInventoryResponseItem
import com.saleman.preference.SessionData
import com.saleman.ui.adapter.InventoryAdapter
import com.saleman.utils.MyProgressDialog
import com.saleman.utils.PopupUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.filter
import kotlin.collections.isNullOrEmpty
import kotlin.collections.set

class BrowseFragment : BaseFragment() {

    private lateinit var rvBrowse: RecyclerView
    private lateinit var txtNoFound: TextView
    var inventoryAdapter = InventoryAdapter()
    var listOfItem = ArrayList<Products>()
    var listOfInventory = ArrayList<SalesInventoryResponseItem>()
    var TAG = "@@@MainActivity"
    private lateinit var pd: MyProgressDialog
    var selectedProductTypeStr=""
    var selectedCompanyStr=""
    lateinit var productTypeList: List<ProductType>
    lateinit var inventoryList: List<SalesInventoryResponseItem>
    lateinit var companyList:List<Company>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_browse, container, false)
        rvBrowse = root.findViewById(R.id.rvBrowse)
        txtNoFound = root.findViewById(R.id.txtNoFound)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pd = MyProgressDialog(requireContext(), R.drawable.icons8_loader)
        setTitle("Inventory")
        showMenu()
        hideBack()
        showButtom()
        getCompanyList()
        getProductTypeList()
        getCategoryList()
        getInventorySales("","","","")
        mainActivity.edtSearch.setText("")
        mainActivity.edtSearch.clearFocus()

        rvBrowse.adapter = inventoryAdapter
        mainActivity.imgSearchText.setOnClickListener {
            val search = mainActivity.edtSearch.text.trim()
            if (!TextUtils.isEmpty(search)) {
                getInventorySales(search.toString(),"","","")
                mainActivity.edtSearch.clearFocus()
            } else {
//                inventoryAdapter.setData(listOfItem, {
//                    val product = listOfItem.get(it)
//                    apiCallingForAddCart(product)
//                })
                mainActivity.edtSearch.clearFocus()
            }
        }
        mainActivity.rlFilter.setOnClickListener {
            PopupUtils.fillterDialog(
                productTypeList,
                companyList,
                requireActivity(), {
                    selectedProductTypeStr= it.productType!!
                    selectedCompanyStr= it.company!!
                    getInventorySales("",selectedCompanyStr,"",selectedProductTypeStr)
//                    apiCallingForProductList(selectedShelfStr,mostOrderedProductBl,selectedProductTypeStr,selectedCompanyStr,selPriceRangeMin,selPriceRangeMax,selPriceMarginMin,selPriceMarginMax)
            })
        }
//        apiCallingForProductList(selectedShelfStr,mostOrderedProductBl,selectedProductTypeStr,selectedCompanyStr,selPriceRangeMin,selPriceRangeMax,selPriceMarginMin,selPriceMarginMax)
    }

    fun apiCallingForProductList(selectedShelf:String,mostOrderedProduct:Boolean,typeId:String,companyId:String,priceMinRange:String,priceMaxRange:String,priceMinMargin:String,priceMaxMargin:String) {
        pd.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["user_id"] = SessionData.getInstance(requireContext()).getUserId()!!
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            params["shelf_life"] = selectedShelf
            params["most_ordered_product"] = if(mostOrderedProduct)"1" else "0"
            params["type_id"] = typeId
            params["company_id"] = companyId
            params["price_min_range"] = priceMinRange
            params["price_max_range"] = priceMaxRange
            params["price_min_margin"] = priceMinMargin
            params["price_max_margin"] = priceMaxMargin
            try {
                val responseOfSales = apiCallingRequest.apiCallingForProducts(params)
                listOfItem.clear()
                listOfItem = responseOfSales.products as ArrayList<Products>
                withContext(Dispatchers.Main) {
                    pd.cancel()
//                    inventoryAdapter.setData(listOfItem, {
//                        val product = listOfItem.get(it)
//                        apiCallingForAddCart(product)
//                    })
                    if(listOfItem!=null || listOfItem.size>0){
                        rvBrowse.visibility = View.VISIBLE
                        txtNoFound.visibility = View.GONE
                    }else{
                        rvBrowse.visibility = View.GONE
                        txtNoFound.visibility = View.VISIBLE
                    }
                }
            } catch (apiEx: IOException) {
                withContext(Dispatchers.Main) {
                    rvBrowse.visibility = View.GONE
                    txtNoFound.visibility = View.VISIBLE
                    pd.cancel()
                }
            }
        }
    }


    public fun getInventorySales(search:String,companyId:String,categoryId:String,typeId:String) {
        pd.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            params["distributor_id"] = SessionData.getInstance(requireContext()).getDistrubuterId()!!
            params["search"] = search
            params["company_id"] = companyId
            params["category_id"] = categoryId
            params["type_id"] = typeId
            try {
                val responseOfCity = apiCallingRequest.getSalesInventory(params)
                if(responseOfCity!=null){
                    withContext(Dispatchers.Main) {
                        listOfInventory.clear()
                        listOfInventory = responseOfCity.products as java.util.ArrayList<SalesInventoryResponseItem>
                        inventoryAdapter.setData(listOfInventory,{

                        })
                        if(listOfInventory!=null && listOfInventory.size>0){
                            rvBrowse.visibility = View.VISIBLE
                            txtNoFound.visibility = View.GONE
                        }else{
                            rvBrowse.visibility = View.GONE
                            txtNoFound.visibility = View.VISIBLE
                        }
                        pd.hide()
                    }
                }
            } catch (apiEx: ApiException) {
                Log.v("@@@","Exception sr"+apiEx.message);
                Log.v("@@@","Exception sr"+apiEx.printStackTrace());
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(),getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show()
                    pd.hide()
                }
            }
        }
    }

    public fun getCompanyList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            try {
                val responseOfCity = apiCallingRequest.getCompanyList(params)
                companyList = java.util.ArrayList()
                withContext(Dispatchers.Main) {
                    companyList = responseOfCity.companies as java.util.ArrayList<Company>
                }
            } catch (apiEx: ApiException) {
                apiEx.printStackTrace()
                withContext(Dispatchers.Main) {
                }
            }
        }
    }

    public fun getProductTypeList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            try {
                val responseOfCity = apiCallingRequest.getTypeList(params)
                productTypeList = java.util.ArrayList()
                withContext(Dispatchers.Main) {
                    productTypeList = responseOfCity.types as java.util.ArrayList<ProductType>
                }
            } catch (apiEx: ApiException) {
                apiEx.printStackTrace()
                withContext(Dispatchers.Main) {
                }
            }
        }
    }

    public fun getCategoryList() {
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["api_token"] = SessionData.getInstance(requireContext()).getToken()!!
            try {
                val responseOfCity = apiCallingRequest.getTypeList(params)
                productTypeList = java.util.ArrayList()
                withContext(Dispatchers.Main) {
                    productTypeList = responseOfCity.types as java.util.ArrayList<ProductType>
                }
            } catch (apiEx: ApiException) {
                apiEx.printStackTrace()
                withContext(Dispatchers.Main) {
                }
            }
        }
    }
}