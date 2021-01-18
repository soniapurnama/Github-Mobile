package com.sonia.github_mobile.ui.detailUser.following

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


class UserFollowingListAdapter (val onItemClicked: OnItemClicked) : RecyclerView.Adapter<UserFollowingListAdapter.UserFollowingListHolder>() {

    var items: ArrayList<User> = arrayListOf()
    var listDataFiltered: ArrayList<User> = arrayListOf()

    class UserFollowingListHolder(view: View) : RecyclerView.ViewHolder(view)

    fun addList(listData: ArrayList<User>) {
        val oldItems = items
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
                UserListDiffCallback(
                        oldItems, listData
                )
        )

        items = listData
        listDataFiltered = listData
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFollowingListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserFollowingListHolder(
                view
        )
    }

    override fun getItemCount(): Int {
        return listDataFiltered.size
    }

    override fun onBindViewHolder(holderUser: UserFollowingListHolder, position: Int) {

        val data = listDataFiltered[position]

        holderUser.itemView.tvUsername.text = data.login

        Glide.with(holderUser.itemView.context)
                .load(data.avatarUrl)
                .into(holderUser.itemView.ivUser)

    }


    class UserListDiffCallback(
            val oldList: ArrayList<User>,
            val newList: ArrayList<User>
    ) : DiffUtil.Callback() {
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



