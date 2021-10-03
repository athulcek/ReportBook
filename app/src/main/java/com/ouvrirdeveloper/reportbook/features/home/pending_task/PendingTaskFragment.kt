package com.ouvrirdeveloper.reportbook.features.home.pending_task

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bushnell.golf.ui.base.BaseFragmentWithBinding
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.FragmentPendingTasksBinding
import com.ouvrirdeveloper.basearc.features.home.HomeActivity
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.recyclerview.PendingTaskItem
import org.koin.android.ext.android.inject


class PendingTaskFragment :
    BaseFragmentWithBinding<FragmentPendingTasksBinding>(R.layout.fragment_pending_tasks) {
    private lateinit var adapter: PendingTaskAdapter
    private val viewModel: HomeViewModel by inject()

    companion object {
        fun newInstance() = PendingTaskFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = PendingTaskAdapter({ item ->
            (requireActivity() as HomeActivity).showDetailsScreen(item)
        })
        binding.rvPendingTasks.adapter = adapter
        binding.rvPendingTasks.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(requireContext(), VERTICAL)

        binding.rvPendingTasks.addItemDecoration(itemDecor)
        setObservers()
    }

    private fun setObservers() {
        viewModel.pendingTask.observe(viewLifecycleOwner, Observer { tasks ->
            adapter.submitList(tasks.map {
                PendingTaskItem(id = it.id ?: 0L, pendingTask = it)
            })
            adapter.notifyDataSetChanged()

        })
    }

}