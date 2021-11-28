package com.saleman.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.common.api.ApiException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.saleman.R
import com.saleman.model.*
import com.saleman.model.businessline.BusinessLineModelResponse
import com.saleman.api.ApiCallingRequest
import com.saleman.model.*
import com.saleman.model.businessline.Businessline
import com.saleman.model.distributor.BusinesslineDis
import com.saleman.model.products.Company
import com.saleman.model.products.ProductRequest
import com.saleman.model.products.ProductType
import com.saleman.preference.SessionData
import com.saleman.ui.MainActivity
import com.saleman.ui.adapter.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


object PopupUtils {


    fun alertMessage(context: Context, msg: String) {
        val dialog = MaterialAlertDialogBuilder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage(msg)
                .setPositiveButton(
                        "Ok", null
                )
                .show()
    }

    fun alertMessageWithCallBack(context: Context, msg: String, callBack: (value: String) -> Unit) {
        val dialog = MaterialAlertDialogBuilder(context)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage(msg)
                .setPositiveButton(
                        "Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        callBack.invoke("ok")
                    }
                }
                ).setCancelable(false)
                .show()
    }


    fun confirmationDailg(context: Context, message: String, callBack: (value: String) -> Unit) {
        val alertDialog: AlertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(context.getString(R.string.app_name))
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                { dialog, which ->
                    dialog.dismiss()
                    callBack.invoke("Yes")
                })
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                { dialog, which -> dialog.dismiss() })
        alertDialog.show()
    }

    fun multiChoiceDailog(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<BusinessLineModel>? = null,
            callBack: (value: String) -> Unit
    ) {
        lateinit var multiSelectionAdapter: MultiSelectionAdapter
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        multiSelectionAdapter = MultiSelectionAdapter({
            val businessLineModel = businessLineModels?.get(it)
            if (businessLineModel?.isSelected!!) {
                businessLineModel.isSelected = false
            } else {
                businessLineModel.isSelected = true
            }
            businessLineModels?.set(it, businessLineModel)
            multiSelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = multiSelectionAdapter
        multiSelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            val newArrayList = ArrayList<String>()
            for (businesmodel in businessLineModels) {
                if (businesmodel.isSelected) {
                    newArrayList.add(businesmodel.name)
                }
            }
            val stringValue = TextUtils.join(",", newArrayList)
            callBack.invoke(stringValue)
            dialog.cancel()


        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }


    fun multiChoiceDailogForBusinessLine(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<Businessline>? = null,
            callBack: (name: String, id: String) -> Unit
    ) {
        lateinit var multiSelectionAdapter: BusinessLineSelectionAdapter
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        multiSelectionAdapter = BusinessLineSelectionAdapter({
            val businessLineModel = businessLineModels?.get(it)
            if (businessLineModel?.isSelected!!) {
                businessLineModel.isSelected = false
            } else {
                businessLineModel.isSelected = true
            }
            businessLineModels?.set(it, businessLineModel)
            multiSelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = multiSelectionAdapter
        multiSelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            val newArrayListOfName = ArrayList<String>()
            val newArrayListOfId = ArrayList<String>()

            for (businesmodel in businessLineModels) {
                if (businesmodel.isSelected) {
                    newArrayListOfName.add(businesmodel.name)
                    newArrayListOfId.add("" + businesmodel.id)
                }
            }
            val stringValueName = TextUtils.join(",", newArrayListOfName)
            val stringValueId = TextUtils.join(",", newArrayListOfName)
            callBack.invoke(stringValueName, stringValueId)
            dialog.cancel()
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun cartItemDialog(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<GetLocation.Location>? = null,
            callBack: (name: String, id: String) -> Unit
    ) {
        lateinit var multiSelectionAdapter: LocationSelectionAdapter
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialog_cart)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        multiSelectionAdapter = LocationSelectionAdapter({
            val businessLineModel = businessLineModels?.get(it)
            if (businessLineModel?.isSelected!!) {
                businessLineModel.isSelected = false
            } else {
                businessLineModel.isSelected = true
            }
            businessLineModels?.set(it, businessLineModel)
            multiSelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = multiSelectionAdapter
        multiSelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            val newArrayListOfName = ArrayList<String>()
            val newArrayListOfId = ArrayList<String>()

            for (businesmodel in businessLineModels) {
                if (businesmodel.isSelected) {
                    newArrayListOfName.add(businesmodel.name!!)
                    newArrayListOfId.add("" + businesmodel.id)
                }
            }
            val stringValueName = TextUtils.join(",", newArrayListOfName)
            val stringValueId = TextUtils.join(",", newArrayListOfName)
            callBack.invoke(stringValueName, stringValueId)
            dialog.cancel()
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailog(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<BusinessLineModel>? = null,
            callBack: (value: String) -> Unit
    ) {
        lateinit var multiSelectionAdapter: MultiSelectionAdapter
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        multiSelectionAdapter = MultiSelectionAdapter({
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (i == it) {
                    businessLineModel.isSelected = true
                } else {
                    businessLineModel.isSelected = false
                }
                businessLineModels.set(i, businessLineModel)
            }
            multiSelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = multiSelectionAdapter
        multiSelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            val newArrayList = ArrayList<String>()
            for (businesmodel in businessLineModels) {
                if (businesmodel.isSelected) {
                    newArrayList.add(businesmodel.name)
                }
            }
            val stringValue = TextUtils.join(",", newArrayList)
            callBack.invoke(stringValue)
            dialog.cancel()


        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailogForCompanyList(
        activity: Activity,
        title: String,
        businessLineModels: List<Company>,
        selectedShelfLife: Company? = null,
        callBack: (value: Company) -> Unit
    ) {
        lateinit var stateSelectionAdapter: CompanyListAdapter
        var stateResponse: Company? = selectedShelfLife
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        stateSelectionAdapter = CompanyListAdapter({
            stateResponse = it
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (businessLineModel.id == it.id) {
                    businessLineModel?.is_selected = !businessLineModel?.is_selected
                }
            }
            stateSelectionAdapter.setData(businessLineModels!! as ArrayList<Company>)
        })
        rvMultiSelect.adapter = stateSelectionAdapter
        stateSelectionAdapter.setData(businessLineModels!! as ArrayList<Company>)
        txtOK.setOnClickListener {
            if (stateResponse != null) {
                callBack.invoke(stateResponse!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailogForProductTypeList(
        activity: Activity,
        title: String,
        businessLineModels: List<ProductType>,
        selectedShelfLife: ProductType? = null,
        callBack: (value: ProductType) -> Unit
    ) {
        lateinit var stateSelectionAdapter: ProductTypeAdapter
        var stateResponse: ProductType? = selectedShelfLife
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        stateSelectionAdapter = ProductTypeAdapter({
            stateResponse = it
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (businessLineModel.id == it.id) {
                    businessLineModel?.is_selected = !businessLineModel?.is_selected
                }
            }
            stateSelectionAdapter.setData(businessLineModels as ArrayList<ProductType>)
        }, selectedShelfLife!!)
        rvMultiSelect.adapter = stateSelectionAdapter
        stateSelectionAdapter.setData(businessLineModels!! as ArrayList<ProductType>)
        txtOK.setOnClickListener {
            if (stateResponse != null) {
                callBack.invoke(stateResponse!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailogForShelfLife(
        activity: Activity,
        title: String,
        businessLineModels: ArrayList<ShelfLife>? = null,
        selectedShelfLife: ShelfLife? = null,
        callBack: (value: ShelfLife) -> Unit
    ) {
        lateinit var stateSelectionAdapter: ShelfLifeAdapter
        var stateResponse: ShelfLife? = selectedShelfLife
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        stateSelectionAdapter = ShelfLifeAdapter({
            stateResponse = it
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (businessLineModel.name.equals(it.name) ) {
                    businessLineModel?.isSelected = !(businessLineModel?.isSelected!!)
                }
                businessLineModels.set(i, businessLineModel)
            }
            stateSelectionAdapter.setData(businessLineModels!!,stateResponse!!)
        },selectedShelfLife!!)
        rvMultiSelect.adapter = stateSelectionAdapter
        stateSelectionAdapter.setData(businessLineModels!!,stateResponse!!)
        txtOK.setOnClickListener {
            if (stateResponse != null) {
                callBack.invoke(stateResponse!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailogForState(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<StateResponse.State>? = null,
            selectedState: StateResponse.State? = null,
            callBack: (value: StateResponse.State) -> Unit
    ) {
        lateinit var stateSelectionAdapter: StateSelectionAdapter
        var stateResponse: StateResponse.State? = selectedState
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        stateSelectionAdapter = StateSelectionAdapter({
            stateResponse = businessLineModels?.get(it)
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (i == it) {
                    businessLineModel?.isSelected = true
                } else {
                    businessLineModel?.isSelected = false
                }
                businessLineModels.set(i, businessLineModel)
            }
            stateSelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = stateSelectionAdapter
        if (businessLineModels != null) {
            stateSelectionAdapter.setData(businessLineModels)
        }
        txtOK.setOnClickListener {
            if (stateResponse != null) {
                callBack.invoke(stateResponse!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    fun singleChoiceDailogForCity(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<CityResponse.City>? = null,
            city: CityResponse.City? = null,
            callBack: (value: CityResponse.City) -> Unit
    ) {
        lateinit var citySelectionAdapter: CitySelectionAdapter
        var selectedCity: CityResponse.City? = city
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        citySelectionAdapter = CitySelectionAdapter({
            selectedCity = businessLineModels?.get(it)
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (i == it) {
                    businessLineModel.isSelected = true
                } else {
                    businessLineModel.isSelected = false
                }
                businessLineModels.set(i, businessLineModel)
            }
            citySelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = citySelectionAdapter
        citySelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            if (selectedCity != null) {
                callBack.invoke(selectedCity!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    var selectedProductType:ProductType? = null
    var selectedCompany:Company? = null
    lateinit var  selectedFilter:ProductRequest

    fun fillterDialog(
            productTypeList: List<ProductType>,
            companyList:List<Company>,
            activity: Activity,
            callBack: (ProductRequest) -> Unit
    ) {

        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_filter)
        val txtApplyFilter = dialog.findViewById<TextView>(R.id.txtApplyFilter)
        val txtClearAll = dialog.findViewById<TextView>(R.id.txtClearAll)
        val imgCross = dialog.findViewById<ImageView>(R.id.imgCross)
        var  iv_product_type = dialog.findViewById<TextView>(R.id.iv_product_type)
        var  iv_company = dialog.findViewById<TextView>(R.id.iv_company)
        var  iv_category = dialog.findViewById<TextView>(R.id.iv_category)

//        if(selectedProductType!=null){
//            iv_product_type.setText(selectedProductType?.name!!.toString())
//        }else{
//            selectedProductType = productTypeList.first()
//        }
//        if(selectedCompany!=null){
//            iv_company.setText(selectedCompany?.name!!.toString())
//        }else{
//            selectedCompany = companyList.first()
//        }

        iv_company.setOnClickListener {
            singleChoiceDailogForCompanyList(
                activity,
                "Select Company  ",
                companyList,
                selectedCompany
            ) {
                selectedCompany = it
                iv_company.text = if (selectedCompany!!.is_selected) selectedCompany?.name else ""
            }
        }

        iv_product_type.setOnClickListener {
            singleChoiceDailogForProductTypeList(
                activity,
                "Select Product type  ",
                productTypeList,
                selectedProductType
            ){
                selectedProductType = it
                iv_product_type.text = if (selectedProductType!!.is_selected) it.name else ""
            }
        }


        imgCross.setOnClickListener {
            dialog.cancel()
         }

        txtApplyFilter.setOnClickListener {
            dialog.cancel()
            if(!this::selectedFilter.isInitialized){
                selectedFilter = ProductRequest("",selectedProductType?.id.toString(),selectedCompany?.id.toString())
            }else{
                selectedFilter = ProductRequest("",selectedProductType?.id.toString(),selectedCompany?.id.toString())
            }
            callBack.invoke(selectedFilter)
        }

        txtClearAll.setOnClickListener {
            dialog.cancel()
            if(!this::selectedFilter.isInitialized){
                selectedFilter = ProductRequest("","","")
            }else{
                selectedFilter = ProductRequest("","","")
            }
            selectedProductType = null
            selectedCompany = null
            callBack.invoke(selectedFilter)
        }

        dialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.show()
    }

    @JvmName("getShelfLife1")
    fun getShelfLife():ArrayList<ShelfLife>{
        var shelfLife = ArrayList<ShelfLife>()
        shelfLife.add(ShelfLife("Day",false))
        shelfLife.add(ShelfLife("Month",false))
        shelfLife.add(ShelfLife("Year",false))
        return  shelfLife
    }

    fun singleChoiceDailogForLocation(
            activity: Activity,
            title: String,
            businessLineModels: ArrayList<GetLocation.Location>? = null,
            location: GetLocation.Location? = null,
            callBack: (value: GetLocation.Location) -> Unit) {
        lateinit var citySelectionAdapter: LocationSelectionAdapter
        var selectedLocation: GetLocation.Location? = location
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_recyler_view)
        val rvMultiSelect = dialog.findViewById<RecyclerView>(R.id.rvMultiSelect)
        val txtOK = dialog.findViewById<TextView>(R.id.txtOK)
        val txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = title
        citySelectionAdapter = LocationSelectionAdapter({
            selectedLocation = businessLineModels?.get(it)
            for (i in 0..businessLineModels?.size!! - 1) {
                val businessLineModel = businessLineModels.get(i)
                if (i == it) {
                    businessLineModel.isSelected = true
                } else {
                    businessLineModel.isSelected = false
                }
                businessLineModels.set(i, businessLineModel)
            }
            citySelectionAdapter.notifyDataSetChanged()
        })
        rvMultiSelect.adapter = citySelectionAdapter
        citySelectionAdapter.setData(businessLineModels!!)
        txtOK.setOnClickListener {
            if (selectedLocation != null) {
                callBack.invoke(selectedLocation!!)
                dialog.cancel()
            }
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }


    fun checkOutDialog(
            activity: Activity,
            total: String,
            totalPrice: String,
            callBack: (String) -> Unit
    ) {
        val dialog = Dialog(activity, android.R.style.Theme_Material_Dialog_Alert)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_check_out)
        val imgCross = dialog.findViewById<ImageView>(R.id.imgCross)
        val txtCheckOut = dialog.findViewById<TextView>(R.id.txtCheckOut)
        val txtTotal = dialog.findViewById<TextView>(R.id.txtTotal)
        val txtTotalPrice = dialog.findViewById<TextView>(R.id.txtTotalPrice)
        txtTotal.setText(total)
        txtTotalPrice.setText("₹ " + totalPrice)

        imgCross.setOnClickListener {
            dialog.cancel()
        }
        txtCheckOut.setOnClickListener {
            dialog.cancel()
            callBack.invoke("")
        }

        imgCross.setOnClickListener {
            dialog.cancel()
        }
        dialog.window!!.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

}