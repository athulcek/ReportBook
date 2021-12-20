package com.ouvrirdeveloper.reportbook.features.home.view_document

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.core.extensions.popBackStack
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.core.utils.StartSnapHelper
import com.ouvrirdeveloper.domain.helpers.extensions.ifLet
import com.ouvrirdeveloper.domain.models.PendingTaskDetail
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentRequisitionDetailBinding
import com.ouvrirdeveloper.reportbook.features.home.HomeActivity
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.recyclerview.BaseRecyclerStateItem
import com.ouvrirdeveloper.reportbook.recyclerview.RequisitionDetailItem
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


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
                    )
                    viewModel.getrequisitionDetail(code, number)
                }
            }
        }
        setHeader()
        val layoutManager = LinearLayoutManager(requireContext())
        val startSnapHelper: SnapHelper = StartSnapHelper()
        startSnapHelper.attachToRecyclerView(binding.rvRequisition)
        adapter = RequisitionDetailAdapter({ viewDetails ->
//            (requireActivity() as HomeActivity).showProjectDetail(viewDetails)
        }, {})
        binding.rvRequisition.adapter = adapter
        binding.rvRequisition.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(requireContext(), VERTICAL)
        R.drawable.divider_decoration.asDrawable(requireContext())
            ?.let { itemDecor.setDrawable(it) }

        //binding.rvRequisition.addItemDecoration(itemDecor)
        setObservers()
    }

    private fun setHeader() {
        binding.header.tvslNo.text = "Sl.No"
        binding.header.tvmaterialCode.text = "MaterialCode"
        binding.header.tvmaterialName.text = "MaterialName"
        binding.header.tvquantity.text = "Quantity"
    }

    private fun setObservers() {
        viewModel.requisitionDetail.observe(viewLifecycleOwner, Observer { tasks ->
            when (tasks.status) {
                Status.SUCCESS -> {
                    getActivity?.hideProgress()
                    ifLet(tasks.data) {
                        adapter.submitList(it.flatMap { it }.map {
                            RequisitionDetailItem(id = it.slNo ?: 0L, requisitionDetail = it)
                        })
                        requisitionDetail = it.flatMap { it }.firstOrNull()
                        requisitionDetail?.apply {
                            binding.item = this
                        }
                    }


                }
                Status.GENERIC_ERROR -> TODO()
                Status.NETWORK_ERROR -> TODO()
                Status.HTTP_ERROR -> TODO()
                Status.INITIAL, Status.LOADING -> {
                    getActivity?.showProgress(backgroundColorRes = R.color.white)
                    adapter.submitList(
                        listOf(BaseRecyclerStateItem.ProgressStateItem(Resource.loading(true)))
                    )
                }
                Status.EMPTY -> {
                    getActivity?.hideProgress()
                    adapter.submitList(
                        listOf(
                            BaseRecyclerStateItem.ErrorStateItem(
                                icon = R.drawable.ic_refresh.asDrawable(
                                    requireContext()
                                )
                            )
                        )
                    )
                }
            }

        })
    }


    override fun onBackPressed() {
        findNavController().popBackStack()
    }

}