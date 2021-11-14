package com.ouvrirdeveloper.reportbook.features.home

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.navigation.Navigation
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.basearc.helper.extensions.setupWithNavController
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.ActivityHomeBinding
import com.ouvrirdeveloper.reportbook.features.home.pending_task.PendingTaskFragment
import com.ouvrirdeveloper.reportbook.features.home.pending_task.PendingTaskFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivityWithBinding<ActivityHomeBinding>(R.layout.activity_home) {


    private val viewmodel by viewModel<HomeViewModel>()
    val pendingTask = PendingTaskFragment.newInstance()
    val settings = PendingTaskFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppBar(
            title = "Home",
            startIcon = R.drawable.ic_home.asDrawable(this),
            backgroundColorRes = R.color.surface
        )
        networkObserver.observe(this, {
            if (it) {
                binding.viewStatus.setBackgroundColor(R.color.green.asColor(this))
            } else {
                binding.viewStatus.setBackgroundColor(
                    R.color.error.asColor(
                        this
                    )
                )
            }

        })

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds =
            listOf(
                R.navigation.home_nav,
                R.navigation.home_settings
            )
        binding.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent
        )
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }


    fun showDetailsScreen(item: PendingTask) {
        val nextAction = PendingTaskFragmentDirections.goToDetails(item.docsrchcode)
        Navigation.findNavController(this, R.id.fragmentContainer).navigate(nextAction)
    }

    fun viewDocument(item: PendingTaskDetail) {
        /*addFragment(
            RequisitionDetailFragment.newInstance(item),
            R.id.fragmentContainer,
            addToStack = true
        )*/
//        findNavController().navigate(R.id.action_support_to_support_detail)
    }

    fun showProjectDetail(requisitionDetail: RequisitionDetail) {
        /*CoroutineScope(Dispatchers.Default).launch {
            addFragment(
                ProjectDetailFragment.newInstance(requisitionDetail),
                R.id.fragmentContainer,
                addToStack = true
            )
        }*/

    }

    fun showBottomSheet(requisitionDetail: RequisitionDetail) {
        /*  addFragment(
              ProjectBottomSheetFragment.newInstance(requisitionDetail),
              R.id.fragmentContainer,
              addToStack = true
          )*/
    }
}