package com.sonia.github_mobile.ui.detailUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.sonia.github_mobile.R
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.ui.detailUser.adapter.ViewPagerAdapter
import com.sonia.github_mobile.ui.detailUser.follower.UserFollowerFragment
import com.sonia.github_mobile.ui.detailUser.following.UserFollowingFragment
import kotlinx.android.synthetic.main.activity_detail_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailUserActivity : AppCompatActivity() , Observer<DetailUserState> {

    val followerFragment = UserFollowerFragment()
    val followingFragment = UserFollowingFragment()
    val mViewModel :DetailUserViewModel by viewModel()


    var user : User? =null
    companion object{
        val KEY_USER_DETAIL ="detail user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        user = intent.getParcelableExtra(KEY_USER_DETAIL)

        mViewModel.state.observe(this,this)
        mViewModel.doLoadDetailUser(username = user?.login.toString())

        setupView()


    }

    private fun setupView() {

        appBar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    //collpasing
                    collapsingToolbar.title = resources.getString(R.string.detail_user)
                    isShow = true
                } else if (isShow) {
                    //expand
                    collapsingToolbar.setTitle("")
                    isShow = false
                }
            }
        })

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(followerFragment,resources.getString(R.string.follower))
        adapter.addFragment(followingFragment,resources.getString(R.string.following))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


        Glide.with(this)
                .load(user?.avatarUrl)
                .into(ivUser)
    }

    override fun onChanged(state: DetailUserState?) {

        when(state){

            is DetailUserState.OnLoading -> {}

            is DetailUserState.OnShowResultDetail -> {
                tvUsername.text = state.userDetail.name
            }

            is DetailUserState.OnError -> {}
        }
    }
}