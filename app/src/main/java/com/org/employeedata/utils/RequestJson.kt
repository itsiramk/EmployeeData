package com.org.employeedata.utils


/*
      Created by Iram
*/

import com.google.gson.JsonObject

public class RequestJson {

    companion object {
        fun create_emp_json(name: String, age: String, salary: String): JsonObject {
            val jsonObject = JsonObject()
            jsonObject.addProperty(AppConstants.EMP_NAME, name)
            jsonObject.addProperty(AppConstants.EMP_SALARY, salary)
            jsonObject.addProperty(AppConstants.EMP_AGE, age)
            return jsonObject
        }
    }
}