package com.ouvrirdeveloper.reportbook.features.home.view_document

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.ouvrirdeveloper.core.ui.BaseBottomSheetDialogFragment
import com.ouvrirdeveloper.domain.helpers.extensions.deserialize
import com.ouvrirdeveloper.domain.helpers.extensions.serialize
import com.ouvrirdeveloper.domain.models.RequisitionDetail
import com.ouvrirdeveloper.reportbook.databinding.BottomSheetFragmentProjectDetailBinding

class ProjectBottomSheetFragment : BaseBottomSheetDialogFragment() {

    lateinit var binding: BottomSheetFragmentProjectDetailBinding

    companion object {
        const val REQUISITIONDETAIL_CODE = "requisitiondetail_code"
        fun newInstance(requisitionDetail: RequisitionDetail): ProjectBottomSheetFragment {
            val fragment = ProjectBottomSheetFragment()
            fragment.arguments = bundleOf(
                REQUISITIONDETAIL_CODE to requisitionDetail.serialize()
            )
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFragmentProjectDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            getString(ProjectDetailFragment.REQUISITIONDETAIL_CODE)?.let {
                binding.item = (it.deserialize(RequisitionDetail::class) as RequisitionDetail)
            }
        }
    }
}