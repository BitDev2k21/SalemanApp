package com.saleman.model.sales

import com.google.gson.annotations.SerializedName

data class InventoryResponse(

	@field:SerializedName("image_path")
	val imagePath: String? = null,

	@field:SerializedName("products")
	val products: List<SalesInventoryResponseItem?>? = null
)

