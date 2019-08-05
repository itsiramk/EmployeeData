package com.org.employeedata.utils


/*
      Created by Iram
*/

import android.content.Context

interface ApiUtils {

     companion object{
        fun getApiService(ctx: Context): ApiService {
            return Rest_Adapter.getClient(ctx).create(ApiService::class.java)
        }

    }




}