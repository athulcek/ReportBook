package com.ouvrirdeveloper.reportbook.features.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentHomeBinding
import com.ouvrirdeveloper.reportbook.features.home.epoxy.HomeEpoxyController
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.ReportType
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment :
    BaseFragmentWithBinding<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by sharedViewModel<HomeViewModel>()
    var controller: HomeEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setSwipeResfresh()
        if (savedInstanceState == null) {
            callApis()
        }

    }

    private fun setSwipeResfresh() {
        binding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launchWhenStarted {
                callApis()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (binding.swipeRefresh.isRefreshing) {
            binding.swipeRefresh.setRefreshing(false);
            binding.swipeRefresh.clearAnimation();
        }
        binding.swipeRefresh.isEnabled = false
    }

    override fun onResume() {
        super.onResume()
        binding.swipeRefresh.isEnabled = true
        setTitle(
            title = R.string.home.asString(requireContext()),
            startIcon = null,
            endIcon = null
        )
    }

    private fun setRecyclerView() {
        controller = HomeEpoxyController(requireContext(), viewModel._onHomeClickListner)
        controller?.apply {
            binding.rvHome.setControllerAndBuildModels(this)
            binding.rvHome.setDelayMsWhenRemovingAdapterOnDetach(200)
            binding.rvHome.setRemoveAdapterWhenDetachedFromWindow(true)
            setObservers()
        }
    }

    private fun callApis() {
        if (getActivity?.isNetworkAvailable == true) {
            viewModel.getPendingTasks()
            viewModel.getmaterialRequestStages()
            viewModel.getPurchaseOrderStage()
            viewModel.getSiteMaterialReceiptStages()
            viewModel.getSupplierInvoiceStages()
        } else {
            showAlert(
                "No internet Please try again",
                positiveButtonText = "Ok",
                onPositiveButtonClick = {})
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.flowComplete.observe(viewLifecycleOwner, {
                if (it == 5) {
                    binding.swipeRefresh.isRefreshing = false
                    viewModel.resetComplete()
                }
            })
            viewModel.onHomeClickListner.collectLatest { item ->

                when (item.first) {
                    ReportType.ApprovalPendings -> {
                        viewModel.initPendingTaskDetails()
                        viewModel.setCurrentType(ReportType.ApprovalPendings)
                        val action =
                            HomeFragmentDirections.actionHomeToPendingTask(item.second as String)
                        try {
                            findNavController().navigate(action)
                        } catch (ex: IllegalArgumentException) {
                            ex.printStackTrace()
//                            showToast("current Des ${findNavController().currentDestination?.label}")
                        }
                    }
                    ReportType.MaterialRequestStages -> {
                        viewModel.initPendingTaskDetails()
                        viewModel.setCurrentType(ReportType.MaterialRequestStages)
                        val action =
                            HomeFragmentDirections.actionHomeToPendingTask(item.second as String)
                        try {
                            findNavController().navigate(action)
                        } catch (ex: IllegalArgumentException) {
                            ex.printStackTrace()
//                            showToast("current Des ${findNavController().currentDestination?.label}")
                        }
                    }
                    else -> {
                        showToast("${item.first}, id==> ${item.second}")
                        /* val intent =
                             Intent("tester.ble.invisawear.bletest.blemodule.ACTION_DATA_AVAILABLE")
                         requireContext().sendBroadcast(intent)*/
                    }
                }
            }
        }

        controller?.apply {
            viewModel.pendingTasks.observe(viewLifecycleOwner, ::doForPendingTasks)
            viewModel.purchaseOrderStage.observe(
                viewLifecycleOwner,
                ::doForPurchaseOrderStage
            )
            viewModel.siteMaterialReceiptStages.observe(
                viewLifecycleOwner,
                ::doForSiteMaterialReceiptStages
            )
            viewModel.supplierInvoiceStages.observe(
                viewLifecycleOwner,
                ::doForSupplierInvoiceStages
            )
            viewModel.materialRequestStages.observe(
                viewLifecycleOwner,
                ::doForMaterialRequestStages
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controller = null
    }
}

