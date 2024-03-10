package com.ashutosh.androidcleanarchitecture.user.presentation.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.androidcleanarchitecture.databinding.RowUserBinding
import com.ashutosh.androidcleanarchitecture.user.domain.User
import com.bumptech.glide.Glide

class UserPagingAdaptor():PagingDataAdapter<User, UserPagingAdaptor.UserViewHolder>(
    UserDiffUtilCallback()
) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data!!)
        Glide.with(holder.binding.root).load(data.avatar).into(holder.binding.ivUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RowUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    inner class UserViewHolder(val binding: RowUserBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data: User){
            binding.txtName.text = "${data.firstName} ${data.lastName}"
            binding.txtEmail.text = data.email
        }
    }

    private class UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}