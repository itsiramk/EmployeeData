package com.org.employeedata.adapter

 /*
     Created by Iram
*/

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.org.employeedata.R
import com.org.employeedata.model.GetEmp_Response

class GetAllEmpAdapter (val userList: List<GetEmp_Response>) : RecyclerView.Adapter<GetAllEmpAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllEmpAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_get_all_emp_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: GetAllEmpAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(getallempResponse : GetEmp_Response) {
            val textViewId = itemView.findViewById(R.id.txt_get_emp_id) as TextView
            val textViewName = itemView.findViewById(R.id.txt_get_emp_name) as TextView
            val textViewAge  = itemView.findViewById(R.id.txt_get_emp_age) as TextView
            val textViewSalary  = itemView.findViewById(R.id.txt_get_emp_salary) as TextView
            textViewId.text = getallempResponse.getId()
            textViewName.text = getallempResponse.getEmployeeName()
            textViewAge.text = getallempResponse.getEmployeeAge()
            textViewSalary.text = getallempResponse.getEmployeeSalary()
        }
    }
}