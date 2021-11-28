package com.saleman.model.sales

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalesPendingPaymentResponse(

	@field:SerializedName("orders")
	val orders: List<OrdersItem?>? = null
) : Parcelable

@Parcelize
data class OrdersItem(

	@field:SerializedName("order_no")
	val orderNo: String? = null,

	@field:SerializedName("shop_id")
	val shopId: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("shop")
	val shop: Shop? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("distributor_id")
	val distributorId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("location_id")
	val locationId: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("approved_at")
    val approved_at: String? = null,

	@field:SerializedName("delivered_at")
	val delivered_at: String? = null,

	@field:SerializedName("paid_at")
	val paid_at: String? = null,

	@field:SerializedName("distributor_details")
	val distributorDetails: DistributorDetails? = null

	) : Parcelable

@Parcelize
data class Shop(

	@field:SerializedName("owner_name")
	val ownerName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("location_id")
	val locationId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("gst_no")
	val gstNo: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("working_hours")
	val workingHours: String? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state_id")
	val stateId: Int? = null,

	@field:SerializedName("city_id")
	val cityId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
