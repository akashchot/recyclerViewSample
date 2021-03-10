package com.adda.addatest.model

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.adda.addatest.net.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Users : BaseObservable() {
    var status: String? = null
    private val usersList: MutableList<User> =
        ArrayList()
    val users =
        MutableLiveData<List<User>>()

    fun addUser(bd: User) {
        usersList.add(bd)
    }

    fun fetchList() {
        val callback: Callback<Users?> = object : Callback<Users?> {
            override fun onResponse(
                call: Call<Users?>,
                response: Response<Users?>
            ) {
                val body = response.body()
                status = body!!.status
                users.setValue(body.usersList)
            }

            override fun onFailure(
                call: Call<Users?>,
                t: Throwable
            ) {
                Log.e("Test", t.message, t)
            }
        }
        Api.api?.users!!.enqueue(callback)
    }
}