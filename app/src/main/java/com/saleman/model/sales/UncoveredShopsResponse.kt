package com.saleman.model.sales

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UncoveredShopsResponse(

	@field:SerializedName("shops")
	val shops: List<ShopsItem?>? = null
) : Parcelable

@Parcelize
data class ShopsItem(

	@field:SerializedName("owner_name")
	val ownerName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("businessline")
	val businessline: List<String?>? = null,

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

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state_id")
	val stateId: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("city_id")
	val cityId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("api_token")
	val apiToken: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: String? = null,

	@field:SerializedName("user_type")
	val userType: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("identity")
	val identity: String? = null,

	@field:SerializedName("contact")
	val contact: String? = null,

	@field:SerializedName("distributor_id")
	val distributorId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("pincode")
	val pincode: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state_id")
	val stateId: Int? = null,

	@field:SerializedName("city_id")
	val cityId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
