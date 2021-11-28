package com.saleman.api

import com.google.gson.JsonObject
import com.saleman.model.*
import com.saleman.model.businessline.BusinessLineModelResponse
import com.saleman.model.cart.AddCartResponse
import com.saleman.model.cart.CartListResponse
import com.saleman.model.distributor.DistributorResponse
import com.saleman.model.products.GetCompanyResponse
import com.saleman.model.products.GetProductTypeResponse
import com.saleman.model.products.ProductResponse
import com.saleman.model.sales.*
import com.saleman.model.shoplist.ShopListResponse

class ApiCallingRequest : com.saleman.api.SafeApiRequest() {

    var apiService: com.saleman.api.ApiService

    init {
        apiService = com.saleman.api.ApiService.Factory.invoke()
    }

    suspend fun apiCallingForLogin(
            params: HashMap<String, String>
    ): LoginResponse {
        return apiRequest { apiService.login(params) }
    }


    suspend fun apiCallingBusinessLine(
            params: HashMap<String, String>
    ): BusinessLineModelResponse {
        return apiRequest { apiService.apiCallingBusinessLine(params) }
    }

    suspend fun apiCallingForDashBoard(
            params: HashMap<String, String>
    ): DashBoardRetailer {
        return apiRequest { apiService.apiForDashBoard(params) }
    }

    suspend fun apiCallingForUpdateDist(
            params: HashMap<String, String>
    ): DistributorResponse {
        return apiRequest { apiService.apiCallingUpdateDist(params) }
    }

    suspend fun apiCallingRegister(
            params: HashMap<String, String>
    ): LoginResponse {
        return apiRequest { apiService.register(params) }
    }

    suspend fun getLocationList(
            params: HashMap<String, String>
    ): GetLocation {
        return apiRequest { apiService.getLocationListApi(params) }
    }

    suspend fun apiCallingAddShop(
            params: HashMap<String, String>
    ): LoginResponse {
        return apiRequest { apiService.apiCallingForAddShop(params) }
    }

    suspend fun apiCallingAUpdateShop(
            params: HashMap<String, String>
    ): LoginResponse {
        return apiRequest { apiService.apiCallingForUpdateShop(params) }
    }

    suspend fun apiCallingForSateResponse(
            params: HashMap<String, String>
    ): StateResponse {
        return apiRequest { apiService.stateList(params) }
    }

    suspend fun apiCallingForCityResponse(
            params: HashMap<String, String>
    ): CityResponse {
        return apiRequest { apiService.cityList(params) }
    }


    suspend fun apiCallingForOrderList(
            params: HashMap<String, String>
    ): PendingOrderResponse {
        return apiRequest { apiService.orderList(params) }
    }

    suspend fun apiCallingForRetailerOrderList(
            params: HashMap<String, String>
    ): SalesPendingOrderResponse {
        return apiRequest { apiService.retailerOrderList(params) }
    }

    suspend fun salesUncoveredShops(
        params: HashMap<String, String>
    ): UncoveredShopsResponse {
        return apiRequest { apiService.salesUncoveredShops(params) }
    }


    suspend fun apiCallingForRetailerPastOrder(
            params: HashMap<String, String>
    ): PendingOrderResponse {
        return apiRequest { apiService.retailerPastOrderList(params) }
    }

    suspend fun apiCallingForShopList(
            params: HashMap<String, String>
    ): ShopListResponse {
        return apiRequest { apiService.apishopList(params) }
    }


    suspend fun apiCartList(
            params: HashMap<String, String>
    ): CartListResponse {
        return apiRequest { apiService.apiCartList(params) }
    }

    suspend fun apiCallingForProducts(
            params: HashMap<String, String>
    ): ProductResponse {
        return apiRequest { apiService.apiCallingForProducts(params) }
    }

    suspend fun addCart(
            params: HashMap<String, String>
    ): AddCartResponse {
        return apiRequest { apiService.addCart(params) }
    }

    suspend fun updateCart(
            params: HashMap<String, String>
    ): AddCartResponse {
        return apiRequest { apiService.updateCart(params) }
    }

    suspend fun getTypeList(
        params: HashMap<String, String>
    ): GetProductTypeResponse {
        return apiRequest { apiService.getTypeList(params) }
    }

    suspend fun getSalesPendingPayment(
        params: HashMap<String, String>
    ): SalesPendingPaymentResponse {
        return apiRequest { apiService.getSalesPendingPayment(params) }
    }

    suspend fun getSalesInventory(
        params: HashMap<String, String>
    ): InventoryResponse {
        return apiRequest { apiService.getSalesInventory(params) }
    }

    suspend fun getCompanyList(
        params: HashMap<String, String>
    ): GetCompanyResponse {
        return apiRequest { apiService.getCompanyList(params) }
    }


    suspend fun deleteCart(
            params: HashMap<String, String>
    ): AddCartResponse {
        return apiRequest { apiService.deleteCart(params) }
    }

    suspend fun createOrder(
            params: HashMap<String, String>
    ): JsonObject {
        return apiRequest { apiService.createOrder(params) }
    }

    suspend fun apiCallingForOperator(
            params: HashMap<String, String>
    ): OperatorsResponse {
        return apiRequest { apiService.apiOperator(params) }
    }

    suspend fun apiDeleteShop(
            params: HashMap<String, String>
    ): String {
        return apiRequest { apiService.deleteShop(params) }
    }

    suspend fun apiCallingInventry(
            params: HashMap<String, String>
    ): InventoryResponse {
        return apiRequest { apiService.apiInventry(params) }
    }


}


