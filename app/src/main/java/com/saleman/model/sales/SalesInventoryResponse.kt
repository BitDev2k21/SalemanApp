package com.saleman.model.sales

import com.google.gson.annotations.SerializedName

data class SalesInventoryResponse(

	@field:SerializedName("SalesInventoryResponse")
	val salesInventoryResponse: List<SalesInventoryResponseItem?>? = null
)

data class Company(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("address_1")
	val address1: String? = null,

	@field:SerializedName("address_2")
	val address2: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("location_id")
	val locationId: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Category(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("parent_id")
	val parentId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Products(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("company_id")
	val companyId: Int? = null,

	@field:SerializedName("businessline_id")
	val businesslineId: Int? = null,

	@field:SerializedName("businessline")
	val businessline: Businessline? = null,

	@field:SerializedName("type_id")
	val typeId: Int? = null,

	@field:SerializedName("min_qty")
	val minQty: String? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("type")
	val type: Type? = null,

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

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Type(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("businessline_id")
	val businesslineId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Businessline(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

//data class SalesInventoryResponseItem(
//
//	@field:SerializedName("updated_at")
//	val updatedAt: Any? = null,
//
//	@field:SerializedName("distributor_id")
//	val distributorId: Int? = null,
//
//	@field:SerializedName("product_id")
//	val productId: Int? = null,
//
//	@field:SerializedName("dist_price")
//	val distPrice: Int? = null,
//
//	@field:SerializedName("created_at")
//	val createdAt: Any? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("inventory")
//	val inventory: Int? = null,
//
//	@field:SerializedName("products")
//	val products: Products? = null
//)

data class SalesInventoryResponseItem(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("company_id")
	val companyId: Int? = null,

	@field:SerializedName("businessline_id")
	val businesslineId: Int? = null,

	@field:SerializedName("businessline")
	val businessline: Businessline? = null,

	@field:SerializedName("type_id")
	val typeId: Int? = null,

	@field:SerializedName("min_qty")
	val minQty: String? = null,

	@field:SerializedName("dist_price")
	val distPrice: Int? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("inventory")
	val inventory: Int? = null,

	@field:SerializedName("type")
	val type: Type? = null,

	@field:SerializedName("exp_validity")
	val expValidity: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("distributor_id")
	val distributorId: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("exp_validity_unit")
	val expValidityUnit: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("status")
	val status: String? = null
)
