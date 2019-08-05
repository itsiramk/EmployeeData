package com.org.employeedata.model

    /*
        Created by Iram
    */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GetEmp_Response {

    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("employee_name")
    @Expose
    private var employeeName: String? = null
    @SerializedName("employee_salary")
    @Expose
    private var employeeSalary: String? = null
    @SerializedName("employee_age")
    @Expose
    private var employeeAge: String? = null
    @SerializedName("profile_image")
    @Expose
    private var profileImage: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getEmployeeName(): String? {
        return employeeName
    }

    fun setEmployeeName(employeeName: String) {
        this.employeeName = employeeName
    }

    fun getEmployeeSalary(): String? {
        return employeeSalary
    }

    fun setEmployeeSalary(employeeSalary: String) {
        this.employeeSalary = employeeSalary
    }

    fun getEmployeeAge(): String? {
        return employeeAge
    }

    fun setEmployeeAge(employeeAge: String) {
        this.employeeAge = employeeAge
    }

    fun getProfileImage(): String? {
        return profileImage
    }

    fun setProfileImage(profileImage: String) {
        this.profileImage = profileImage
    }
}