package com.sonia.github_mobile.ui.detailUser.following

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
import kotlinx.android.synthetic.main.fragment_user_follower.progressCircular
import kotlinx.android.synthetic.main.fragment_user_following.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFollowingFragment : Fragment() ,Observer<UserFollowingState> ,OnItemClicked{

    val mViewModel : UserFollowingViewModel by viewModel()
    lateinit var adapterList : UserFollowingListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as DetailUserActivity

        mViewModel.state.observe(this,this)
        mViewModel.doLoadFollowing(activity.user?.login.toString())
        adapterList = UserFollowingListAdapter(this)

    }

    override fun onResume() {
        super.onResume()
        setupView()
    }
    private fun setupView() {

        recyclerViewFollowing.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFollowing.adapter = adapterList
    }
    override fun onChanged(state: UserFollowingState?) {

        when (state){

            is UserFollowingState.OnShowResultList -> {
                val arraylist = arrayListOf<User>()
                arraylist.addAll(state.listData)
                adapterList.addList(arraylist)
            }
            is UserFollowingState.OnLoading -> {
                if(state.isLoading){
                    recyclerViewFollowing.visibility = View.INVISIBLE
                    progressCircular.visibility = View.VISIBLE
                }else{
                    recyclerViewFollowing.visibility = View.VISIBLE
                    progressCircular.visibility = View.INVISIBLE
                }
            }
            is UserFollowingState.OnError -> {

            }
        }
    }
}