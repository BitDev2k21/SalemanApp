package com.saleman.model.sales

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DistributorDetails(

    @field:SerializedName("owner_name")
    val ownerName: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("latitude")
    val latitude: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("routes")
    val routes: String? = null,

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

    @field:SerializedName("credit_period")
    val creditPeriod: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("state_id")
    val stateId: Int? = null,

    @field:SerializedName("city_id")
    val cityId: Int? = null,

    @field:SerializedName("longitude")
    val longitude: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable


