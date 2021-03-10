package com.adda.addatest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adda.addatest.databinding.ActivityMainBinding
import com.adda.addatest.model.User
import com.adda.addatest.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        val activityBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        if (savedInstanceState == null) {
            viewModel.init()
        }
        activityBinding.vm = viewModel
        setupListUpdate()
    }

    private fun setupListUpdate() {
        viewModel.loading!!.set(View.VISIBLE)
        viewModel.fetchList()
        viewModel.getUsers().observe(
            this,
            Observer<List<User>> { users ->
                viewModel.loading!!.set(View.GONE)
                if (users.size == 0) {
                    viewModel.showEmpty!!.set(View.VISIBLE)
                } else {
                    viewModel.showEmpty!!.set(View.GONE)
                    viewModel.setUsersInAdapter(users)
                }
            })
        setupListClick()
    }

    private fun setupListClick() {
        viewModel.selected.observe(this, Observer<User?> { user ->
            if (user != null) {
                Toast.makeText(
                    this,
                    "You selected a " + user.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}