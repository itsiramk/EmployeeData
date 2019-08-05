package com.org.employeedata.view


/*
      Created by Iram
*/

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.org.employeedata.R
import com.org.employeedata.model.GetEmp_Response
import com.org.employeedata.utils.AppConstants
import com.org.employeedata.viewmodel.GetSingleEmpViewModel
import kotlinx.android.synthetic.main.fragment_get_single_emp.*

class GetSingleEmployeeFragment : Fragment() {

    private fun fetchEmpData(id: String) {

        mGetSingleEmpViewModel = ViewModelProviders.of(this).get(GetSingleEmpViewModel()::class.java)
        mGetSingleEmpViewModel!!.getSingleEmp(ctx!!, id).observe(this, object : Observer<GetEmp_Response> {
            override fun onChanged(empDetails: GetEmp_Response?) {
                try {
                    if (empDetails != null) {
                        ll_emp_details.visibility = View.VISIBLE
                        txt_get_emp_name.text = empDetails.getEmployeeName()
                        txt_get_emp_age.text = empDetails.getEmployeeAge()
                        txt_get_emp_salary.text = empDetails.getEmployeeSalary()
                    } else {
                        Toast.makeText(ctx, AppConstants.UNABLE_TO_FETCH_DATA, Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(ctx, AppConstants.UNABLE_TO_FETCH_DATA, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private var mGetSingleEmpViewModel: GetSingleEmpViewModel? = null
    private var ctx: Context? = null
    private var str_emp_id: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_get_single_emp,
                container, false)

        return view
    }

    private fun hideKeyboard(view: View) {
        view?.apply {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ctx = context

        btn_submit!!.setOnClickListener {

            str_emp_id = et_emp_id.text.toString().trim()
            hideKeyboard(view)
            if (str_emp_id != null) {
                fetchEmpData(str_emp_id!!)
            } else {
                txt_emp_id_layout.error = AppConstants.INVALID_EMP_ID;
            }

        }
    }

}