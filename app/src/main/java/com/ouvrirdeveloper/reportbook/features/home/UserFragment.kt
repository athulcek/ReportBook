package com.ouvrirdeveloper.basearc.features.home

import android.os.Bundle
import android.view.View
import com.bushnell.golf.ui.base.BaseFragmentWithBinding
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.FragmentUserBinding

class UserFragment : BaseFragmentWithBinding<FragmentUserBinding>(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity
    }
}