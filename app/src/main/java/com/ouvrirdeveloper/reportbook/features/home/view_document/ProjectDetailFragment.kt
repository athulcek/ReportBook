package com.ouvrirdeveloper.reportbook.features.home.view_document

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.domain.helpers.extensions.deserialize
import com.ouvrirdeveloper.domain.helpers.extensions.serialize
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentProjectDetailBinding
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProjectDetailFragment :
    BaseFragmentWithBinding<FragmentProjectDetailBinding>(R.layout.fragment_project_detail) {

    private val viewModel by sharedViewModel<HomeViewModel>()

    companion object {
        const val REQUISITIONDETAIL_CODE = "requisitiondetail_code"
       suspend fun newInstance(requisitionDetail: RequisitionDetail): ProjectDetailFragment {
            val fragment = ProjectDetailFragment()
            fragment.arguments = bundleOf(
                REQUISITIONDETAIL_CODE to requisitionDetail.serialize()
            )
            return fragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getString(REQUISITIONDETAIL_CODE)?.let {
                binding.item = (it.deserialize(RequisitionDetail::class) as RequisitionDetail)
            }
        }
    }

}