package com.org.employeedata.viewmodel


/*
      Created by Iram
*/

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import com.org.employeedata.model.GetEmp_Response
import com.org.employeedata.utils.ApiService
import com.org.employeedata.utils.ApiUtils
import com.org.employeedata.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetSingleEmpViewModel : ViewModel() {

    private var get_single_emp: MutableLiveData<GetEmp_Response>? = null
    private var apiService: ApiService? = null

    fun getSingleEmp(context: Context,id:String): LiveData<GetEmp_Response> {
        //if the list is null
        //if (get_single_emp == null) {
        get_single_emp = MutableLiveData<GetEmp_Response>()
        getSingleEmpData(context,id)
        // }
        return get_single_emp!!
    }

    fun getSingleEmpData(context: Context,id:String) {

        apiService = ApiUtils.getApiService(context)
        apiService!!.getSingleEmpData(id).enqueue(object : Callback <GetEmp_Response> {
            override fun onResponse(call: Call <GetEmp_Response>, response: Response <GetEmp_Response>) {
                if (response.message() != null) {
                    get_single_emp!!.setValue(response.body())
                }
            }

            override fun onFailure(call: Call <GetEmp_Response>, t: Throwable) {
                Toast.makeText(context, AppConstants.INTERNET_ERROR, Toast.LENGTH_SHORT).show()
            }
        })


    }
}