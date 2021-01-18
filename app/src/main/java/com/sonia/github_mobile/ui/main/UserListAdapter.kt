package com.sonia.github_mobile.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sonia.github_mobile.R
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.utils.view.OnItemClicked
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter (val onItemClicked: OnItemClicked) :RecyclerView.Adapter<UserListAdapter.UserListHolder>(){

    var items :ArrayList<User> = arrayListOf()
    var listDataFiltered : ArrayList<User> = arrayListOf()
    class UserListHolder(view:View) :RecyclerView.ViewHolder(view)

    fun addList(listData :ArrayList<User>){
        val oldItems = items
        val diffResult :DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserListDiffCallback(
                oldItems, listData
            )
        )

        items = listData
        listDataFiltered = listData
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return UserListHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return listDataFiltered.size
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {

        val data = listDataFiltered[position]

        holder.itemView.tvUsername.text = data.login

        Glide.with(holder.itemView.context)
            .load(data.avatarUrl)
            .into(holder.itemView.ivUser)

        holder.itemView.setOnClickListener {
            onItemClicked.onUserClicked(data)
        }
    }


    class UserListDiffCallback(
        val oldList : ArrayList<User>,
        val newList : ArrayList<User>
    ):DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldList[oldItemPosition].login == newList[newItemPosition].login)
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }



}