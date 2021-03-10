package com.adda.addatest.viewmodel;

import android.view.View;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adda.addatest.R;
import com.adda.addatest.adapter.UsersAdapter;
import com.adda.addatest.model.User;
import com.adda.addatest.model.Users;

import java.util.List;


public class UserViewModel extends ViewModel {

    private Users users;
    private UsersAdapter adapter;
    public MutableLiveData<User> selected;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init() {
        users = new Users();
        selected = new MutableLiveData<>();
        adapter = new UsersAdapter(R.layout.list_item, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public void fetchList() {
        users.fetchList();
    }

    public MutableLiveData<List<User>> getUsers() {
        return users.getUsers();
    }

    public UsersAdapter getAdapter() {
        return adapter;
    }

    public void setUsersInAdapter(List<User> users) {
        this.adapter.setUsers(users);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<User> getSelected() {
        return selected;
    }

    public void onItemClick(Integer index) {
        User db = getUserAt(index);
        selected.setValue(db);
    }

    public User getUserAt(Integer index) {
        if (users.getUsers().getValue() != null &&
                index != null &&
                users.getUsers().getValue().size() > index) {
            return users.getUsers().getValue().get(index);
        }
        return null;
    }

}
