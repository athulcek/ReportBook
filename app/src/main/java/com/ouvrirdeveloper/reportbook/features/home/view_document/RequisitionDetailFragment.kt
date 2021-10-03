package com.ouvrirdeveloper.reportbook.features.home.pending_task

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.bushnell.golf.ui.base.BaseFragmentWithBinding
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.basearc.databinding.FragmentRequisitionDetailBinding
import com.ouvrirdeveloper.basearc.features.home.HomeActivity
import com.ouvrirdeveloper.core.extensions.popBackStack
import com.ouvrirdeveloper.core.utils.StartSnapHelper
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.home.view_document.RequisitionDetailAdapter
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.RequisitionDetailItem
import org.koin.android.viewmodel.ext.android.sharedViewModel


class RequisitionDetailFragment :
    BaseFragmentWithBinding<FragmentRequisitionDetailBinding>(R.layout.fragment_requisition_detail) {
    private lateinit var adapter: RequisitionDetailAdapter
    private val viewModel by sharedViewModel<HomeViewModel>()
    private var requisitionDetail: RequisitionDetail? = null

    companion object {

        const val SRCHDOCSRCHCODE_CODE = "srchDOCSRCHCODE"
        const val SRCHDOCNUMBER_CODE = "srchDOCNUMBER"
        fun newInstance(item: PendingTaskDetail): RequisitionDetailFragment {
            val fragment = RequisitionDetailFragment()
            fragment.arguments = bundleOf(
                SRCHDOCSRCHCODE_CODE to item.docsrchcode,
                SRCHDOCNUMBER_CODE to item.docnumber
            )
            return fragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            getString(SRCHDOCSRCHCODE_CODE)?.let { code ->
                getString(SRCHDOCNUMBER_CODE)?.let { number ->
                    setTitle(
                        number,
                        endIcon = R.drawable.ic_refresh.asDrawable(requireContext()),
                        onEndIconClick = {
                            requisitionDetail?.let {
                                (requireActivity() as HomeActivity).showBottomSheet(it)
                            }

                        })
                    viewModel.getrequisitionDetail(code, number)
                }
            }
        }
        val layoutManager = LinearLayoutManager(requireContext())
        val startSnapHelper: SnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding.rvRequisition)
        adapter = RequisitionDetailAdapter({ viewDetails ->
            (requireActivity() as HomeActivity).showProjectDetail(viewDetails)
        })
        binding.rvRequisition.adapter = adapter
        binding.rvRequisition.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(requireContext(), VERTICAL)
        R.drawable.divider_decoration.asDrawable(requireContext())
            ?.let { itemDecor.setDrawable(it) }

        //binding.rvRequisition.addItemDecoration(itemDecor)
        setObservers()
    }

    private fun setObservers() {
        viewModel.requisitionDetail.observe(viewLifecycleOwner, Observer { tasks ->

            when (tasks.status) {
                Status.SUCCESS -> {
                    adapter.submitList(tasks.data?.map {
                        RequisitionDetailItem(id = it.slNo ?: 0L, requisitionDetail = it)
                    })
                    requisitionDetail = tasks.data?.firstOrNull()
                    adapter.notifyDataSetChanged()
                }
                Status.GENERIC_ERROR -> TODO()
                Status.NETWORK_ERROR -> TODO()
                Status.HTTP_ERROR -> TODO()
                Status.LOADING -> {
                    adapter.submitList(tasks.data?.map {
                        BaseRecyclerStateItem.ProgressStateItem(Resource.loading(true))
                    })
                    adapter.notifyDataSetChanged()
                }
                Status.INITIAL -> TODO()
                Status.EMPTY -> TODO()
            }

        })
    }


    override fun onBackPressed(): Boolean {
        (requireActivity() as HomeActivity).popBackStack()
        return true
    }

}