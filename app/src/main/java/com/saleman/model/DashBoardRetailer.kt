package com.saleman.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DashBoardRetailer {
    @SerializedName("data")
    @Expose
     var data: Data? = null

    class Data {
        @SerializedName("pending_orders")
        @Expose
         var pending_orders: Int? = null

        @SerializedName("pending_payments")
        @Expose
         var pending_payments: Int? = null

        @SerializedName("out_of_stock")
        @Expose
        var out_of_stock: Int? = null

      }

}