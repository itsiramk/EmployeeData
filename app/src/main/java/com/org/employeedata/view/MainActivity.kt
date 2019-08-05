package com.org.employeedata.view


/*
      Created by Iram
*/

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.org.employeedata.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*


class MainActivity : AppCompatActivity() {

    var navigationPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        setUpDrawerLayout()

        navigationPosition = R.id.navItemGetEmpAll
        navigateToFragment(GetAllEmployeeFragment())
        navigationView.setCheckedItem(navigationPosition)
        toolbar.title = getString(R.string.get_emp_all)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navItemGetEmpAll -> {
                    toolbar.title = getString(R.string.get_emp_all)
                    navigationPosition = R.id.navItemGetEmpAll
                    navigateToFragment(GetAllEmployeeFragment())
                }
                R.id.navItemGetSingle -> {
                    toolbar.title = getString(R.string.get_emp)
                    navigationPosition = R.id.navItemGetSingle
                    navigateToFragment(GetSingleEmployeeFragment())
                }
                R.id.navItemCreateEmp -> {
                    toolbar.title = getString(R.string.create_emp)
                    navigationPosition = R.id.navItemCreateEmp
                    navigateToFragment(CreateEmployeeFragment())
                }

            }
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        changeNavigationHeaderInfo()

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(p0: Int) {
            }

            override fun onDrawerSlide(p0: View, p1: Float) {
            }

            override fun onDrawerClosed(p0: View) {
            }

            override fun onDrawerOpened(p0: View) {
            }
        })
    }

    private fun changeNavigationHeaderInfo() {
        val headerView = navigationView.getHeaderView(0)
        headerView.textEmail.text = "admin@gmail.com"
    }

    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START)
        }

        if (navigationPosition == R.id.navItemGetEmpAll) {
            finish()
        } else {
            navigationPosition = R.id.navItemGetEmpAll
            navigateToFragment(CreateEmployeeFragment())
            navigationView.setCheckedItem(navigationPosition)
            toolbar.title = getString(R.string.get_emp_all)
        }
    }

}