package com.ouvrirdeveloper.reportbook.features.signin

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.databinding.ActivitySignInBinding
import com.ouvrirdeveloper.basearc.features.home.HomeActivity
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.core.constants.AppConstant
import com.ouvrirdeveloper.core.extensions.launchActivityAsRoot
import com.ouvrirdeveloper.core.extensions.showKeyboard
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.features.base.*
import org.koin.android.ext.android.inject


class SignInActivity : BaseActivityWithBinding<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val viewModel: SignInViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.let {
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
                    onLoginSuccess()
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
                Status.LOADING -> {
                    showLoading(getString(R.string.signing_in_progress))
                }
            }
        })

        viewModel.validation.observe(this, {
            when (it) {
                SignInViewModel.EmailRequired -> {
                    binding?.tilEmail?.error = getString(R.string.userId_required_error)
                    binding?.signInEmail?.requestFocus()
                    binding?.signInEmail?.let { it1 -> showKeyboard(it1) }
                }

                SignInViewModel.EmailInvalid -> {
                    binding?.tilEmail?.error = getString(R.string.invalid_userId_error)
                    binding?.signInEmail?.requestFocus()
                    binding?.signInEmail?.let { it1 -> showKeyboard(it1) }
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
        observePendingTask()
    }

    private fun observePendingTask() {
        viewModel.getPendingTasks()
        viewModel.pendingTasks.observe(this, Observer {
            when (it.status) {
                Status.EMPTY, Status.SUCCESS -> {
                    hideLoading()
                    launchActivityAsRoot<HomeActivity> { }
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
                    showLoading(getString(R.string.fetching_pending_tasks))
                }

            }

        })
    }


}