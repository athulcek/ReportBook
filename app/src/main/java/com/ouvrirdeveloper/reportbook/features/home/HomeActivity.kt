package com.ouvrirdeveloper.basearc.features.home

import android.os.Bundle
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.basearc.databinding.ActivityHomeBinding
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.core.extensions.addFragment
import com.ouvrirdeveloper.core.extensions.getCurrentFragment
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.home.pending_task.PendingTaskDetailFragment
import com.ouvrirdeveloper.reportbook.features.home.pending_task.PendingTaskFragment
import com.ouvrirdeveloper.reportbook.features.home.pending_task.RequisitionDetailFragment
import com.ouvrirdeveloper.reportbook.features.home.view_document.ProjectBottomSheetFragment
import com.ouvrirdeveloper.reportbook.features.home.view_document.ProjectDetailFragment
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivityWithBinding<ActivityHomeBinding>(R.layout.activity_home) {


    private val viewModel by viewModel<HomeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppBar("Home", R.drawable.ic_home.asDrawable(this))
        addFragment(PendingTaskFragment.newInstance(), R.id.fragmentContainer)
        networkObserver.observe(this, {
            if (it) {
                binding.viewStatus.setBackgroundColor(R.color.green.asColor(this))
            } else {
                binding.viewStatus.setBackgroundColor(
                    com.ouvrirdeveloper.basearc.R.color.red.asColor(
                        this
                    )
                )
            }

        })

        supportFragmentManager.addOnBackStackChangedListener {
            getCurrentFragment()?.onResume()
        }
    }

    fun showDetailsScreen(item: PendingTask) {
        addFragment(
            PendingTaskDetailFragment.newInstance(item),
            R.id.fragmentContainer,
            addToStack = true
        )
    }

    fun viewDocument(item: PendingTaskDetail) {
        addFragment(
            RequisitionDetailFragment.newInstance(item),
            R.id.fragmentContainer,
            addToStack = true
        )

    }

    fun showProjectDetail(requisitionDetail: RequisitionDetail) {
        addFragment(
            ProjectDetailFragment.newInstance(requisitionDetail),
            R.id.fragmentContainer,
            addToStack = true
        )
    }

    fun showBottomSheet(requisitionDetail: RequisitionDetail) {
        addFragment(
            ProjectBottomSheetFragment.newInstance(requisitionDetail),
            R.id.fragmentContainer,
            addToStack = true
        )
    }
}