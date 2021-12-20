package com.ouvrirdeveloper.reportbook.features.home.epoxy.models

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.kotlinsample.helpers.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.makeVisible
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.RvItemProgressBinding


@EpoxyModelClass(layout = R.layout.rv_item_progress)
abstract class LoadingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<RvItemProgressBinding>() {
    @EpoxyAttribute
    lateinit var status: Resource<Boolean>

    @EpoxyAttribute
    lateinit var loadingText: String


    @EpoxyAttribute
    lateinit var retry: () -> Unit
    override fun RvItemProgressBinding.bind() {
        when (status.status) {
            Status.INITIAL, Status.SUCCESS -> {
                ivRetry.gone()
                inLayoutProgressBar.gone()
                tvLoading.gone()
                layoutIcon.gone()
            }
            Status.EMPTY -> {
                ivRetry.gone()
                inLayoutProgressBar.gone()
                tvLoading.gone()
                layoutIcon.makeVisible()
                lottieLoading.apply {
                    setAnimation(R.raw.tumbleweed_rolling)
                    playAnimation()
                }

            }
            Status.GENERIC_ERROR,
            Status.NETWORK_ERROR,
            Status.HTTP_ERROR -> {
                layoutIcon.gone()
                inLayoutProgressBar.gone()
                ivRetry.makeVisible()
                tvLoading.makeVisible()
                tvLoading.text = loadingText
            }
            Status.LOADING -> {
                layoutIcon.gone()
                ivRetry.gone()
                inLayoutProgressBar.makeVisible()
                tvLoading.gone()
            }
        }
        ivRetry.setOnClickListener {
            retry.invoke()

        }
        tvLoading.setOnClickListener {
            retry.invoke()
        }
    }

    override fun RvItemProgressBinding.unbind() {
        ivRetry.setOnClickListener(null)
    }

}