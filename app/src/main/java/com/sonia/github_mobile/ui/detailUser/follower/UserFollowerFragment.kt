package com.sonia.github_mobile.ui.detailUser.follower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonia.github_mobile.R
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.ui.detailUser.DetailUserActivity
import com.sonia.github_mobile.utils.view.OnItemClicked
import kotlinx.android.synthetic.main.fragment_user_follower.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFollowerFragment : Fragment(),Observer<UserFollowerState> ,OnItemClicked {

    val mViewModel : UserFollowerViewModel by viewModel()
    lateinit var adapterList : UserFollowerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_follower, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as DetailUserActivity

        mViewModel.state.observe(this,this)
        mViewModel.doLoadFollower(activity.user?.login.toString())
        adapterList = UserFollowerListAdapter(this)


    }

    override fun onResume() {
        super.onResume()
       setupView()
    }


    private fun setupView() {

        recyclerViewFollower.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFollower.adapter = adapterList
    }


    override fun onChanged(state: UserFollowerState?) {

        when (state){

            is UserFollowerState.OnShowResultList -> {
                val arraylist = arrayListOf<User>()
                arraylist.addAll(state.listData)
                adapterList.addList(arraylist)
            }
            is UserFollowerState.OnLoading -> {
                if(state.isLoading){
                    recyclerViewFollower.visibility = View.INVISIBLE
                    progressCircular.visibility = View.VISIBLE
                }else{
                    recyclerViewFollower.visibility = View.VISIBLE
                    progressCircular.visibility = View.INVISIBLE
                }
            }
            is UserFollowerState.OnError -> {}
        }
    }

}