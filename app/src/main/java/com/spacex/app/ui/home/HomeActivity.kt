package com.spacex.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.spacex.app.R
import com.spacex.app.databinding.ActivityMainBinding
import com.spacex.app.ui.detail.RocketDetailActivity
import com.spacex.app.ui.home.viewmodel.HomeViewModel
import com.spacex.app.util.imageloader.ImageLoader
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(R.layout.activity_main) {

    private val imageLoader: ImageLoader by inject()
    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var rocketListAdapter: RocketListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViews()
        setupCollectors()

        if (isNetworkAvailable(this)) {
            homeViewModel.fetchRockets()
        } else {
            getRocketsFromDb()
        }
    }

    private fun setupCollectors() {
        collectFlow(homeViewModel.rocketListResponse) {
            rocketListAdapter.setData(it)
        }

        collectFlow(homeViewModel.isLoading) {
            showLoading(it)
        }

        collectFlow(homeViewModel.errorResponse) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRocketsFromDb() {
        homeViewModel.rockets.observe(
            this
        ) {
            showEmptyState(it.isEmpty())
            rocketListAdapter.setData(it) }
    }

    private fun showEmptyState(isEmpty: Boolean) {
        binding.tvNotData.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun setupViews() {
        binding.apply {
            rocketListAdapter = RocketListAdapter(
                imageLoader = imageLoader,
                onItemClick = {
                    startDetailActivity(it.id)
                }
            )
            rocketListAdapter.setData(arrayListOf())
            rocketListRv.apply {
                adapter = rocketListAdapter
            }
        }
    }

    //region ui progress
    private fun showLoading(isLoading: Boolean) {
        if (isLoading)
            binding.progressHome.visibility = View.VISIBLE
        else
            binding.progressHome.visibility = View.GONE
    }
    //endregion

    companion object {
        const val ROCKET_ID = "rocket_id"
        const val BUNDLE_ROCKET_ID = "bundle_rocket_id"
    }

    private fun startDetailActivity(rocketId: String) {
        val bundle = Bundle()
        bundle.putString(ROCKET_ID, rocketId)
        val detailIntent = Intent(this, RocketDetailActivity::class.java)
        detailIntent.putExtra(BUNDLE_ROCKET_ID, bundle)
        startActivity(detailIntent)
    }

}