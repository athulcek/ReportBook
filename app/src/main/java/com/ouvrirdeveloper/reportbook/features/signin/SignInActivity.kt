package com.ouvrirdeveloper.reportbook.features.signin

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.core.constants.AppConstant
import com.ouvrirdeveloper.core.extensions.applogd
import com.ouvrirdeveloper.core.extensions.launchActivityAsRoot
import com.ouvrirdeveloper.core.extensions.showKeyboard
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.ActivitySignInBinding
import com.ouvrirdeveloper.reportbook.features.base.handleApiError
import com.ouvrirdeveloper.reportbook.features.base.setDisplayHomeEnabled
import com.ouvrirdeveloper.reportbook.features.base.setToolbar
import com.ouvrirdeveloper.reportbook.features.base.setToolbarTitle
import com.ouvrirdeveloper.reportbook.features.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class SignInActivity : BaseActivityWithBinding<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val viewModel: SignInViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.let {
            it.viewModel = viewModel
        }
        setToolbar()
        setToolbarTitle(getString(R.string.land_sign_in))
        setDisplayHomeEnabled(intent.getBooleanExtra(AppConstant.KEY_SHOW_BACK_BUTTON, false))
        setClickListeners()
        setupObservers()

    }

    private fun setClickListeners() {

    }

    override fun onResume() {
        super.onResume()
        binding?.scrollView?.scrollTo(0, 0)
    }

    private fun setupObservers() {
        viewModel.loginDetail.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.userId?.let {
                        FirebaseCrashlytics.getInstance().setUserId(it)
                    }
                    onLoginSuccess()
                }
                Status.GENERIC_ERROR,
                Status.NETWORK_ERROR,
                Status.HTTP_ERROR -> {
                    CoroutineScope(Dispatchers.Main).launch {
                        hideProgress()
                        handleApiError(it) {
                            when (it.errorCode) {
                                401,
                                404 -> {
                                    showToast(getString(R.string.invalid_credentials))
                                    launchActivityAsRoot<SignInActivity> {}
                                    finish()
                                    true
                                }
                                else -> false
                            }
                        }
                    }
                }
                Status.LOADING -> {
                    showProgress(message = getString(R.string.signing_in_progress))
                }
            }
        })

        viewModel.validation.observe(this, {
            when (it) {
                SignInViewModel.EmailRequired -> {
                    binding?.tilUserId?.error = getString(R.string.userId_required_error)
                    binding?.signInCompanyId?.requestFocus()
                    binding?.signInCompanyId?.let { it1 -> showKeyboard(it1) }
                }

                SignInViewModel.EmailInvalid -> {
                    binding?.tilUserId?.error = getString(R.string.invalid_userId_error)
                    binding?.signInCompanyId?.requestFocus()
                    binding?.signInCompanyId?.let { it1 -> showKeyboard(it1) }
                }

                SignInViewModel.PasswordRequired -> {
                    binding?.tilPassword?.error = getString(R.string.password_required_error)
                    if (viewModel.validateEmail(false)) {
                        binding?.signInPassword?.requestFocus()
                        binding?.signInPassword?.let { it1 -> showKeyboard(it1) }
                    }
                }
            }
        })


    }

    private fun onLoginSuccess() {
//        observePendingTask()

        if (binding.signInCheckBox.isChecked) {
            viewModel.saveToDisk(
                viewModel.userId.value.toString(),
                viewModel.password.value.toString(),
                viewModel.companyId.value?.toIntOrNull() ?: 0
            )
        }
        launchActivityAsRoot<HomeActivity> { }
        finish()
    }

    private fun observePendingTask() {
        viewModel.getPendingTasks()
        viewModel.pendingTasks.observe(this, Observer {
            when (it.status) {
                Status.EMPTY, Status.SUCCESS -> {
                    hideProgress()
                    launchActivityAsRoot<HomeActivity> { }
                    finish()
                }
                Status.GENERIC_ERROR,
                Status.NETWORK_ERROR,
                Status.HTTP_ERROR -> handleApiError(it) {
                    when (it.errorCode) {
                        401,
                        404 -> {
                            showToast(getString(R.string.invalid_credentials))
                            true
                        }
                        else -> false
                    }
                }
                Status.LOADING,
                Status.INITIAL -> {
                    showProgress(message = getString(R.string.fetching_pending_tasks))
                }

            }

        })
    }


}