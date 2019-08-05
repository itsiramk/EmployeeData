package com.org.employeedata.viewmodel


/*
      Created by Iram
*/

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.org.employeedata.model.GetEmp_Response
import com.org.employeedata.utils.ApiService
import com.org.employeedata.utils.ApiUtils
import com.org.employeedata.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllEmpViewModel : ViewModel() {

    private var get_all_emp: MutableLiveData<List<GetEmp_Response>>? = null
    private var apiService: ApiService? = null

    fun getAllEmp(context: Context): LiveData<List<GetEmp_Response>> {
        //if the list is null
        //if (get_all_emp == null) {
        get_all_emp = MutableLiveData<List<GetEmp_Response>>()
        getAllEmpData(context)
        // }
        return get_all_emp!!
    }

    fun getAllEmpData(context: Context) {

        apiService = ApiUtils.getApiService(context)
        apiService!!.getAllEmpData().enqueue(object : Callback<List<GetEmp_Response>> {
            override fun onResponse(call: Call<List<GetEmp_Response>>, response: Response<List<GetEmp_Response>>) {
                Log.d("Response>>>", response.body()!!.toString())
                if (response.message() != null) {
                    get_all_emp!!.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<GetEmp_Response>>, t: Throwable) {
                Toast.makeText(context, AppConstants.INTERNET_ERROR, Toast.LENGTH_SHORT).show()
            }
        })


    }
}