package com.org.employeedata.view


/*
      Created by Iram
*/

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.org.employeedata.R
import com.org.employeedata.adapter.GetAllEmpAdapter
import com.org.employeedata.model.GetEmp_Response
import com.org.employeedata.viewmodel.GetAllEmpViewModel

class GetAllEmployeeFragment : Fragment() {

    private fun fetchEmpData() {

        mGetAllEmpViewModel = ViewModelProviders.of(this).get(GetAllEmpViewModel()::class.java)
        mGetAllEmpViewModel!!.getAllEmp(ctx!!).observe(this, object : Observer<List<GetEmp_Response>> {
            override fun onChanged(empDetails: List<GetEmp_Response>?) {
                if (empDetails!!.size != null) {

                    recyclerView!!.layoutManager = LinearLayoutManager(ctx, LinearLayout.VERTICAL, false)
                    val adapter = GetAllEmpAdapter(empDetails)
                    recyclerView!!.adapter = adapter
                }
            }
        })
    }

    private var mGetAllEmpViewModel: GetAllEmpViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var ctx: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_get_all_emp,
                container, false)
        recyclerView = view.findViewById(R.id.rv_emp) as RecyclerView

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctx = context
        fetchEmpData()
    }

}