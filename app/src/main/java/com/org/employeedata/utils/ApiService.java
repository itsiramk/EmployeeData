package com.org.employeedata.utils;


/*
  Created by Iram
*/

import com.google.gson.JsonObject;
import com.org.employeedata.model.CreateEmp_Response;
import com.org.employeedata.model.GetEmp_Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {



    @POST("create")
    Call<CreateEmp_Response> createEmployee(@Body JsonObject jsonObject);

    @GET("employees")
    Call<List<GetEmp_Response>> getAllEmpData();

    @GET("employee/{id}")
    Call <GetEmp_Response> getSingleEmpData(@Path("id") String id);
}
