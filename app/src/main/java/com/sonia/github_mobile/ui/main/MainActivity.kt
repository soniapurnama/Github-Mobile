package com.sonia.github_mobile.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonia.github_mobile.R
import com.sonia.github_mobile.data.response.User
import com.sonia.github_mobile.ui.detailUser.DetailUserActivity
import com.sonia.github_mobile.utils.view.OnItemClicked
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() ,Observer<MainState> ,OnItemClicked {

    val mViewModel : MainViewModel by viewModel()
    lateinit var adapterList : UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarSearch)
        mViewModel.state.observe(this,this)


        adapterList = UserListAdapter(this)
        recyclerViewUser.layoutManager = LinearLayoutManager(this)
        recyclerViewUser.adapter = adapterList


    }

    override fun onChanged(state: MainState?) {

        when (state) {

            is MainState.OnLoading -> {
                if (state.isLoading){
                    recyclerViewUser.visibility = View.INVISIBLE
                    progressCircularMain.visibility = View.VISIBLE

                    textView.visibility = View.INVISIBLE
                    animationSearch.visibility = View.INVISIBLE
                }else{
                    recyclerViewUser.visibility = View.VISIBLE
                    progressCircularMain.visibility = View.INVISIBLE
                }
            }

            is MainState.OnShowResultSearch -> {
                val arrayList = arrayListOf<User>()
                arrayList.addAll(state.listData)
                adapterList.addList(arrayList)
            }
        }
    }

    override fun onUserClicked(user: User) {
        super.onUserClicked(user)
        val intent = Intent(this,DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.KEY_USER_DETAIL,user)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mViewModel.doSearchUser(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)


    }
}