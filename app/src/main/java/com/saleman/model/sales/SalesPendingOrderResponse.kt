package com.saleman.model.sales

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SalesPendingOrderResponse(

	@field:SerializedName("image_path")
	val imagePath: String? = null,

	@field:SerializedName("orders")
	val orders: List<SalesPendingOrdersItem?>? = null
) : Parcelable

@Parcelize
data class OrderitemsItem(

	@field:SerializedName("requested_qty")
	val requestedQty: Int? = null,

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("total_price")
	val totalPrice: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,


	@field:SerializedName("approved_qty")
	val approvedQty: String? = null,

	@field:SerializedName("unit_price")
	val unitPrice: Int? = null,

	@field:SerializedName("order_id")
	val orderId: Int? = null
) : Parcelable

@Parcelize
data class Product(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("company_id")
	val companyId: Int? = null,

	@field:SerializedName("businessline_id")
	val businesslineId: Int? = null,

	@field:SerializedName("type_id")
	val typeId: Int? = null,

	@field:SerializedName("min_qty")
	val minQty: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("exp_validity")
	val expValidity: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("exp_validity_unit")
	val expValidityUnit: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class SalesPendingOrdersItem(

	@field:SerializedName("order_no")
	val orderNo: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("shop")
	val shop: Shop? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("location_id")
	val locationId: Int? = null,

	@field:SerializedName("orderitems")
	val orderitems: List<OrderitemsItem?>? = null,

	@field:SerializedName("shop_id")
	val shopId: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("distributor_id")
	val distributorId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

