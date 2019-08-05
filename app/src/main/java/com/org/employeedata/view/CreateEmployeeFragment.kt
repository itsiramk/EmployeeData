package com.org.employeedata.view


/*
      Created by Iram
*/

import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.org.employeedata.R
import com.org.employeedata.model.CreateEmp_Response
import com.org.employeedata.utils.AppConstants
import com.org.employeedata.viewmodel.CreateEmpViewModel
import kotlinx.android.synthetic.main.fragment_create_emp.*
import java.text.SimpleDateFormat
import java.util.*

class CreateEmployeeFragment : Fragment(), View.OnTouchListener,View.OnClickListener {
    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        when (view) {
            et_age -> {
                when (motionEvent!!.getAction()) {
                    MotionEvent.ACTION_DOWN -> {
              }
                    MotionEvent.ACTION_UP -> {

                        mDatePickerDialog!!.show();

                    }
                }
            }

        }

        return true

    }
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.btn_save -> {
                saveEmpData()
            }
            else -> {
            }
        }
    }

    private fun saveEmpData(){

        mCreateEmpViewModel = ViewModelProviders.of(this).get(CreateEmpViewModel()::class.java)
        mCreateEmpViewModel!!.createEmp(ctx!!,et_name!!.text.toString().trim(),et_age!!.text.toString().trim(),et_salary!!.text.toString().trim()).observe(this, object : Observer<CreateEmp_Response> {
            override fun onChanged(empDetails: CreateEmp_Response?) {

                try {
                    if (empDetails != null) {
                        Toast.makeText(ctx, AppConstants.DATA_SAVED, Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(ctx, AppConstants.UNABLE_TO_SAVE_DATA, Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    Toast.makeText(ctx, AppConstants.UNABLE_TO_SAVE_DATA, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private var mDatePickerDialog: DatePickerDialog? = null
    private var mCreateEmpViewModel: CreateEmpViewModel? = null
    private var ctx: Context?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_create_emp,
                container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctx = context
        et_age!!.setOnTouchListener(this)
        btn_save!!.setOnClickListener(this)
        setDateTimeField()


    }

    private fun setDateTimeField() {

        val newCalendar = Calendar.getInstance()
        var fdate: String? = null;
        mDatePickerDialog = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val newDate = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            val sd = SimpleDateFormat("dd-MM-yyyy")
            val startDate = newDate.getTime()
            fdate = sd.format(startDate)

            onDateSet(mDatePickerDialog!!, year, monthOfYear, dayOfMonth)

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH))
        mDatePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()


    }


    fun onDateSet(view: DatePickerDialog, year: Int, month: Int, day: Int) {
        val userAge = GregorianCalendar(year, month, day)
        val minAdultAge = GregorianCalendar()
        val maxAdultAge = GregorianCalendar()
        minAdultAge.add(Calendar.YEAR, -AppConstants.MIN_AGE_LIMIT)
        maxAdultAge.add(Calendar.YEAR, +AppConstants.MAX_AGE_LIMIT)
        if (minAdultAge.before(userAge)) {
            txt_age_layout.setError(AppConstants.ENTER_VALID_DATE)
            et_age.setText(AppConstants.BLANK_TEXT)


        } else {
            txt_age_layout.setError(AppConstants.BLANK_TEXT)
            et_age.setText(getAge(year, month, day))

        }
    }

    private fun getAge(year: Int, month: Int, day: Int): String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.set(year, month, day)

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageInt = age

        return ageInt.toString()
    }
}