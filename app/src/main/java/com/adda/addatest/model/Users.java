package com.adda.addatest.model;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.adda.addatest.net.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Users extends BaseObservable {
    private String status;
    private List<User> usersList = new ArrayList<>();
    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addUser(User bd) {
        usersList.add(bd);
    }

    public MutableLiveData<List<User>> getUsers() {
        return users;
    }

    public void fetchList() {
        Callback<Users> callback = new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users body = response.body();
                status = body.status;
                users.setValue(body.usersList);
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getUsers().enqueue(callback);
    }
}
