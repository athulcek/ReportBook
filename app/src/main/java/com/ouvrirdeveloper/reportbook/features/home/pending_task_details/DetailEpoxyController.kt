package com.ouvrirdeveloper.reportbook.features.home.pending_task_details

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.group
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.domain.helpers.extensions.ifLet
import com.ouvrirdeveloper.domain.models.*
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.features.home.epoxy.data.HomeData
import com.ouvrirdeveloper.reportbook.features.home.epoxy.models.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

class DetailEpoxyController(
    val context: Context,
    val action: MutableSharedFlow<Any>,
    val retry: MutableSharedFlow<Boolean>
) :
    EpoxyController() {

    lateinit var width: String
    val itemsPerPage = 2.45f
    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        width = computeCardWidth(context).toString()


    }

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var pendingTaskDetail: List<PendingTaskDetailsEpoxyModel> = emptyList()
    var loadingStatus: Resource<Boolean> = Resource.initial(null)


    override fun buildModels() {


        if (loadingStatus.status == Status.SUCCESS) {
            buildPendingTaskDetailView()
        } else {
            loadingEpoxyHolder {
                id("loading_view")
                status(this@DetailEpoxyController.loadingStatus)
                loadingText(R.string.download_failed.asString(this@DetailEpoxyController.context))
                retry {
                    this@DetailEpoxyController.scope.launch {
                        this@DetailEpoxyController.retry.emit(true)
                    }
                }

            }
        }
    }


    private fun buildPendingTaskDetailView() {

        group {
            id("group_my_approval_pendings_divider")
            layout(R.layout.epoxy_vertical_linear_group)
            verticalGridCarousel {
                id("get_pendingTaskDetails")
                models(this@DetailEpoxyController.pendingTaskDetail)
            }
        }
    }


    private fun computeCardWidth(context: Context): Int {
        return (context.getResources().getDisplayMetrics().widthPixels
                - context.getResources().getDimensionPixelSize(R.dimen.peek_width)
                - context.getResources().getDimensionPixelSize(R.dimen.item_spacing))
    }

    fun doForPendingTaskDetails(items: Resource<List<Any>?>) {
        CoroutineScope(Dispatchers.IO).launch {
            if (items.isSuccess()) {
                loadingStatus = Resource.loading(true)
                requestModelBuild()
                ifLet(items.data) {
                    pendingTaskDetail = HomeData.getPendingTaskDetails(
                        it.flatMap { it },
                        action = {
                            this@DetailEpoxyController.scope.launch {
                                this@DetailEpoxyController.action.emit(it)
                            }
                        }
                    )
                    delay(500)
                    if (pendingTaskDetail.isNullOrEmpty()) {
                        loadingStatus = Resource.empty(true)
                    } else {
                        loadingStatus = Resource.success(true)
                    }
                    requestModelBuild()
                }
            } else {
                when (items.status) {
                    Status.SUCCESS -> loadingStatus = Resource.success(true)
                    Status.GENERIC_ERROR -> loadingStatus = Resource.genericError("", null)
                    Status.NETWORK_ERROR -> loadingStatus = Resource.httpError(0, "", null)
                    Status.HTTP_ERROR -> loadingStatus = Resource.httpError(0, "", null)
                    Status.LOADING -> loadingStatus = Resource.loading(true)
                    Status.INITIAL -> loadingStatus = Resource.initial(true)
                    Status.EMPTY -> loadingStatus = Resource.empty(true)
                }
                requestModelBuild()
            }
        }


    }


}