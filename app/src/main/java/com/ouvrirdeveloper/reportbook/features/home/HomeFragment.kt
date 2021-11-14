package com.ouvrirdeveloper.reportbook.features.home

import android.os.Bundle
import android.view.View
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentHomeBinding
import com.ouvrirdeveloper.reportbook.features.home.epoxy.HomeEpoxyController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment :
    BaseFragmentWithBinding<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by sharedViewModel<HomeViewModel>()
    lateinit var controller: HomeEpoxyController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        callApis()
        setObservers()
    }

    private fun setRecyclerView() {
        controller = HomeEpoxyController(requireContext(), {

        })
        binding.rvHome.setControllerAndBuildModels(controller)
    }

    private fun callApis() {
        viewModel.getPendingTasks()
        viewModel.getmaterialRequestStages()
        viewModel.getPurchaseOrderStage()
        viewModel.getSiteMaterialReceiptStages()
        viewModel.getSupplierInvoiceStages()
    }

    private fun setObservers() {
        viewModel.pendingTasks.observe(viewLifecycleOwner, controller::doForPendingTasks)
        viewModel.purchaseOrderStage.observe(
            viewLifecycleOwner,
            controller::doForPurchaseOrderStage
        )
        viewModel.siteMaterialReceiptStages.observe(
            viewLifecycleOwner,
            controller::doForSiteMaterialReceiptStages
        )
        viewModel.supplierInvoiceStages.observe(
            viewLifecycleOwner,
            controller::doForSupplierInvoiceStages
        )
        viewModel.materialRequestStages.observe(
            viewLifecycleOwner,
            controller::doForMaterialRequestStages
        )
    }

}

