package com.ouvrirdeveloper.reportbook.features.home.pending_task

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.FragmentPendingTasksBinding
import com.ouvrirdeveloper.basearc.features.home.HomeActivity
import com.ouvrirdeveloper.core.extensions.popBackStack
import com.ouvrirdeveloper.core.utils.StartSnapHelper
import com.ouvrirdeveloper.domain.models.PendingTask
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.home.pending_task_details.PendingTaskDetailAdapter
import com.ouvrirdeveloper.reportbook.recyclerview.PendingTaskDetailItem
import org.koin.android.ext.android.inject


class PendingTaskDetailFragment :
    BaseFragmentWithBinding<FragmentPendingTasksBinding>(R.layout.fragment_pending_tasks) {
    private lateinit var adapter: PendingTaskDetailAdapter
    private val viewModel: HomeViewModel by inject()

    companion object {
        const val DOCSRCHCODE = "docsrchcode"
        fun newInstance(item: PendingTask): PendingTaskDetailFragment {
            val fragment = PendingTaskDetailFragment()
            fragment.arguments = bundleOf(
                DOCSRCHCODE to item.docsrchcode
            )
            return fragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getString(DOCSRCHCODE)?.let {
                viewModel.getPendingTaskDetail(it)
            }
        }
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
            adapter.submitList(tasks.map {
                PendingTaskDetailItem(id = it.srNo ?: 0L, pendingTask = it)
            })
            adapter.notifyDataSetChanged()
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