package com.spacex.app.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.spacex.app.R
import com.spacex.app.databinding.ActivityRocketDetailBinding
import com.spacex.app.domain.model.RocketDetail
import com.spacex.app.ui.home.BaseActivity
import com.spacex.app.ui.home.HomeActivity.Companion.BUNDLE_ROCKET_ID
import com.spacex.app.ui.home.HomeActivity.Companion.ROCKET_ID
import com.spacex.app.ui.home.viewmodel.HomeViewModel
import com.spacex.app.util.Utils
import com.spacex.app.util.imageloader.ImageLoader
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketDetailActivity : BaseActivity(R.layout.activity_rocket_detail) {

    private lateinit var rocketId: String
    private val imageLoader: ImageLoader by inject()
    private val homeViewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityRocketDetailBinding
    private lateinit var flickerImagesAdapter: FlickerImagesAdapter
    val sliderHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRocketDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (intent.hasExtra(BUNDLE_ROCKET_ID)) {
            val bundle = intent.getBundleExtra(BUNDLE_ROCKET_ID)
            bundle?.let {
                rocketId = it.getString(ROCKET_ID) ?: String()
            }
        }

        if (rocketId.isNotEmpty()) {
            if (isNetworkAvailable(this)) {
                homeViewModel.fetchRocketDetail(rocketId)
            } else {
                showEmptyState(true)
                getRocketDetailFromDb(rocketId)
            }
        }

        setupViews()
        setupCollectors()
    }

    private fun setupCollectors() {
        collectFlow(homeViewModel.rocketDetailResponse) {
            binding.apply {
                setData(it)
            }
        }

        collectFlow(homeViewModel.isLoading) {
            showLoading(it)
        }

        collectFlow(homeViewModel.errorResponse) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun getRocketDetailFromDb(rocketId: String) {
        homeViewModel.getRocketDetailFromDb(rocketId).observe(this) {
            binding.apply {
                setData(it)
            }
        }

    }

    private fun ActivityRocketDetailBinding.setData(rocketDetailValue: RocketDetail) {

        showEmptyState(rocketDetailValue.description.isEmpty())
        binding.detailToolbar.title = rocketDetailValue.name
        rocketDetail = rocketDetailValue
        tvActiveStatus.text = Utils.applyColorSpanToLabel(
            this@RocketDetailActivity,
            getString(R.string.active_status),
            if (rocketDetailValue.activeStatus) "Active" else "Inactive",
            if (rocketDetailValue.activeStatus) R.color.green else R.color.red
        )
        flickerImagesAdapter.setData(rocketDetailValue.flickerImages)
        setUpViewPager()

    }

    private fun showEmptyState(isEmpty: Boolean) {
        binding.tvNotData.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.dataGroup.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun setupViews() {
        binding.apply {

            //initializing the adapter
            flickerImagesAdapter = FlickerImagesAdapter(imageLoader)
        }
    }

    //region ui progress
    private fun showLoading(isLoading: Boolean) {
        if (isLoading)
            binding.progress.visibility = View.VISIBLE
        else
            binding.progress.visibility = View.GONE
    }
//endregion

    private fun setUpViewPager() {

        binding.vpFlicker.adapter = flickerImagesAdapter
        //set the orientation of the viewpager using ViewPager2.orientation
        binding.vpFlicker.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        //select any page you want as your starting page
        val currentPageIndex = 0
        binding.vpFlicker.currentItem = currentPageIndex
        // registering for page change callback
        binding.vpFlicker.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 3000) // slide duration 3 seconds
                }
            }
        )
    }

    private var sliderRunnable: Runnable = Runnable {
        if (binding.vpFlicker.currentItem == (flickerImagesAdapter.itemCount - 1)) {
            binding.vpFlicker.currentItem = 0
        } else {
            binding.vpFlicker.currentItem = binding.vpFlicker.currentItem + 1
        }
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}