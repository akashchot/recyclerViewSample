package com.adda.addatest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.adda.addatest.BR
import com.adda.addatest.adapter.UsersAdapter.GenericViewHolder
import com.adda.addatest.model.User
import com.adda.addatest.viewmodel.UserViewModel

class UsersAdapter(
    @param:LayoutRes private val layoutId: Int, private val viewModel: UserViewModel) : RecyclerView.Adapter<GenericViewHolder>() {
    private var users: List<User>? = null
    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return if (users == null) 0 else users!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun setUsers(users: List<User>?) {
        this.users = users
    }

    inner class GenericViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: UserViewModel?, position: Int?) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }

    }

}