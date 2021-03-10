package com.adda.addatest.viewmodel

import android.view.View
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adda.addatest.R
import com.adda.addatest.adapter.UsersAdapter
import com.adda.addatest.model.User
import com.adda.addatest.model.Users

class UserViewModel : ViewModel() {
    var users: Users? = null
    var adapter: UsersAdapter? = null
        private set
    lateinit var selected: MutableLiveData<User?>
    var images: ObservableArrayMap<String, String>? = null
    @JvmField
    var loading: ObservableInt? = null
    @JvmField
    var showEmpty: ObservableInt? = null
    fun init() {
        users = Users()
        selected = MutableLiveData()
        adapter = UsersAdapter(R.layout.list_item, this)
        images = ObservableArrayMap()
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)
    }

    fun fetchList() {
        users!!.fetchList()
    }

    fun getUsers(): MutableLiveData<List<User>> {
        return users!!.users
    }

    fun setUsersInAdapter(users: List<User>?) {
        adapter!!.setUsers(users)
        adapter!!.notifyDataSetChanged()
    }

    fun onItemClick(index: Int?) {
        val db = getUserAt(index)
        selected!!.value = db
    }

    fun getUserAt(index: Int?): User? {
        return if (users!!.users.value != null && index != null && users!!.users.value!!.size > index) {
            users!!.users.value!![index]
        } else null
    }
}