package com.ouvrirdeveloper.reportbook.features.home.pending_task_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.core.extensions.onSearch
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.core.ui.elements.*
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.constant.NavigationConstants
import com.ouvrirdeveloper.reportbook.databinding.FragmentPendingTasksBinding
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.ReportType
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment :
    BaseFragmentWithBinding<FragmentPendingTasksBinding>(R.layout.fragment_pending_tasks) {
    var controller: DetailEpoxyController? = null
    private val viewModel by sharedViewModel<HomeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            getData()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        return view
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setSearch()
    }

    @FlowPreview
    private fun setSearch() {
        val watcher = MutableSharedFlow<String>()
        lifecycleScope.launchWhenStarted {
            watcher.debounce(500)
                .collectLatest {
                    doFilter(it)
                }
        }
        binding.edtSearch.doAfterTextChanged {
            lifecycleScope.launchWhenStarted {
                watcher.emit(it.toString())
            }
        }
        binding.edtSearchLayout.setStartIconOnClickListener {
            doFilter(binding.edtSearch.text.toString())
        }
        binding.edtSearchLayout.setEndIconOnClickListener {
            binding.edtSearch.setText("")
        }
        binding.edtSearch.onSearch {
            doFilter(binding.edtSearch.text.toString())
        }

    }

    private fun doFilter(searchText: String?) {
        when (viewModel.currentType) {
            ReportType.ApprovalPendings -> {
                var filteredData = viewModel.pendingTaskDetails.value
                if (searchText.isNullOrEmpty().not()) {
                    filteredData?.apply {
                        filteredData = Resource(
                            data = this.data?.filter {
                                it.docnumber.contains(searchText.toString())
                            },
                            message = this.message,
                            errorCode = this.errorCode,
                            status = this.status
                        )
                    }
                }
                filteredData?.apply {
                    controller?.doForPendingTaskDetails(this)
                }
            }
            ReportType.MaterialRequestStages -> {
                controller?.doForPendingTaskDetails(Resource.empty(emptyList()))
            }
            null -> TODO()
        }
    }

    private fun getData() {
        lifecycleScope.launchWhenStarted {
            arguments?.apply {
                getString(NavigationConstants.DOCSRCHCODE, "")?.let {
                    setTitle(
                        startIcon = R.drawable.ic_back_arrow.asDrawable(requireContext()),
                        title = it,
                        onStartIconClick = {
                            onBackPressed()
                        }
                    )
                    when (viewModel.currentType) {
                        ReportType.ApprovalPendings -> viewModel.getPendingTaskDetail(it)
                        ReportType.MaterialRequestStages -> {

                        }
                        null -> {
                        }
                    }

                }

            }
        }
    }

    private fun setRecyclerView() {
        controller = DetailEpoxyController(
            requireContext(),
            viewModel.taskDetailAction(),
            viewModel.taskDetailRetry()
        )
        controller?.apply {
            binding.rvPendingTasks.setControllerAndBuildModels(this)
            binding.rvPendingTasks.setDelayMsWhenRemovingAdapterOnDetach(200)
            binding.rvPendingTasks.setRemoveAdapterWhenDetachedFromWindow(true)
            setObservers()
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.onTaskDetailAction.collectLatest { action ->
                when (action) {
                    is PendingTaskDetail -> {
                        AwesomeDialog.build(requireActivity())
                            .title("${action.project}")
                            .cancelable(true)
                            .body("${action.docnumber}\n\nDocument Value:${action.docvalue}")
                            .icon(R.drawable.ic_decision)
                            .onAction("${action.btnview}") {

                            }
                            .onPositive("${action.btnapprove}") {
                                println("positive")
                            }
                            .onNegative("${action.btnreject}") {
                                println("Reject")
                            }
                    }
                }

            }

        }
        lifecycleScope.launchWhenStarted {
            viewModel.onTaskDetailRetry.collect {

                if (getActivity?.isNetworkAvailable == true) {
                    getData()
                } else {
                    showAlert(
                        "No internet Please try again",
                        positiveButtonText = "Ok",
                        onPositiveButtonClick = {})
                }
            }
        }
        viewModel.pendingTaskDetails.observe(
            viewLifecycleOwner,
            {
                doFilter(binding.edtSearch.text.toString())
            }
        )

    }


    override fun onBackPressed() {
        findNavController().navigateUp()
    }

}