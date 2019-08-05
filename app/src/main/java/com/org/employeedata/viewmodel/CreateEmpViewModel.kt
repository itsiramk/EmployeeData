package com.org.employeedata.viewmodel


/*
      Created by Iram
*/

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import com.org.employeedata.model.CreateEmp_Response
import com.org.employeedata.utils.ApiService
import com.org.employeedata.utils.ApiUtils
import com.org.employeedata.utils.AppConstants
import com.org.employeedata.utils.RequestJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateEmpViewModel : ViewModel() {

    private var create_emp: MutableLiveData<CreateEmp_Response>? = null
    private var apiService: ApiService? = null

    fun createEmp(context: Context, name: String, age: String,sal:String): LiveData<CreateEmp_Response> {
        //if the list is null
        //if (create_emp == null) {
        create_emp = MutableLiveData<CreateEmp_Response>()
        createNewEmpData(context, name, age, sal)
        // }
        return create_emp!!
    }

    fun createNewEmpData(context: Context, name: String, age: String, salary: String) {

        apiService = ApiUtils.getApiService(context)
        apiService!!.createEmployee(RequestJson.create_emp_json(name,age,salary)).enqueue(object : Callback<CreateEmp_Response> {
            override fun onResponse(call: Call<CreateEmp_Response>, response: Response<CreateEmp_Response>) {
                if (response.message() != null) {
                    create_emp!!.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<CreateEmp_Response>, t: Throwable) {
                Toast.makeText(context,AppConstants.INTERNET_ERROR,Toast.LENGTH_SHORT).show()

            }
        })


    }

}