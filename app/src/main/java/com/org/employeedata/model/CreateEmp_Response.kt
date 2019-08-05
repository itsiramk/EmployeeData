package com.org.employeedata.model

/*
      Created by Iram
*/
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CreateEmp_Response {

    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("salary")
    @Expose
    private var salary: String? = null
    @SerializedName("age")
    @Expose
    private var age: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getSalary(): String? {
        return salary
    }

    fun setSalary(salary: String) {
        this.salary = salary
    }

    fun getAge(): String? {
        return age
    }

    fun setAge(age: String) {
        this.age = age
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }
}