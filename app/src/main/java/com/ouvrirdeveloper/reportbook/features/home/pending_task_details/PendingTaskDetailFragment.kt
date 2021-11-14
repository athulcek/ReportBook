package com.ouvrirdeveloper.reportbook.features.home.pending_task

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.ouvrirdeveloper.core.extensions.popBackStack
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.core.utils.StartSnapHelper
import com.ouvrirdeveloper.domain.helpers.extensions.ifLet
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentPendingTasksBinding
import com.ouvrirdeveloper.reportbook.features.home.HomeActivity
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.home.pending_task_details.PendingTaskDetailAdapter
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.PendingTaskDetailItem
import org.koin.android.ext.android.inject


class PendingTaskDetailFragment :
    BaseFragmentWithBinding<FragmentPendingTasksBinding>(R.layout.fragment_pending_tasks) {
    private lateinit var adapter: PendingTaskDetailAdapter
    private val viewModel: HomeViewModel by inject()
    val args: PendingTaskDetailFragmentArgs by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.docsrchcode?.let { viewModel.getPendingTaskDetail(it) }
        val layoutManager = LinearLayoutManager(requireContext())
        val startSnapHelper: SnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding.rvPendingTasks)
        adapter = PendingTaskDetailAdapter({ viewDocument ->
            (requireActivity() as HomeActivity).viewDocument(viewDocument)
        }, { approve ->

        }, { reject ->

        })
        binding.rvPendingTasks.adapter = adapter
        binding.rvPendingTasks.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(requireContext(), VERTICAL)

        binding.rvPendingTasks.addItemDecoration(itemDecor)
        setObservers()
    }

    private fun setObservers() {
        viewModel.pendingTaskDetails.observe(viewLifecycleOwner, Observer { tasks ->
            when (tasks.status) {
                Status.SUCCESS -> ifLet(tasks.data) {
                    adapter.submitList(it.flatMap { it }.map {
                        PendingTaskDetailItem(id = it.srNo ?: 0L, pendingTask = it)
                    })
//                    adapter.notifyDataSetChanged()
                }
                Status.GENERIC_ERROR -> TODO()
                Status.NETWORK_ERROR -> TODO()
                Status.HTTP_ERROR -> TODO()
                Status.INITIAL, Status.LOADING -> {
                    adapter.submitList(
                        listOf(BaseRecyclerStateItem.ProgressStateItem(Resource.loading(true)))
                    )
                }
                Status.EMPTY -> TODO()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        setTitle("Pending Task")
    }


    override fun onBackPressed(): Boolean {
        (requireActivity() as HomeActivity).popBackStack()
        return true
    }

}