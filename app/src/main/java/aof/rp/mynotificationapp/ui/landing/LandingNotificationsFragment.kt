package aof.rp.mynotificationapp.ui.landing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import aof.rp.mynotificationapp.R
import aof.rp.mynotificationapp.base.customview.error.model.ErrorStateUi
import aof.rp.mynotificationapp.base.extensions.gone
import aof.rp.mynotificationapp.base.extensions.visible
import aof.rp.mynotificationapp.databinding.LandingNotificationsFragmentBinding
import aof.rp.mynotificationapp.ui.landing.adapter.LandingNotificationAdapter
import aof.rp.mynotificationapp.ui.landing.uimodel.LandingNotificationsUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingNotificationsFragment : Fragment() {

    private lateinit var binding: LandingNotificationsFragmentBinding

    private val viewModel: LandingNotificationsViewModel by viewModels()

    private val notificationAdapter by lazy { LandingNotificationAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchUserId()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LandingNotificationsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observable()
        setupView()
    }

    private fun observable() {
        with(viewModel) {
            landingNotificationsUi.observe(viewLifecycleOwner, ::observeLandingNotificationUi)
        }
    }

    private fun observeLandingNotificationUi(uiModel: LandingNotificationsUi) {
        when (uiModel.uiState) {
            is LandingNotificationsUi.UiState.Error -> {
                hideLoading()
                contentGone()
                setViewErrorState()
            }

            is LandingNotificationsUi.UiState.Loading -> loading()
            is LandingNotificationsUi.UiState.Success -> {
                hideLoading()
                setupView(uiModel.uiState.notificationData)
            }
        }
    }

    private fun setupView(uiModel: LandingNotificationsUi.ContentUi? = null) {
        setHeaderView(uiModel?.profile)
        setupNotificationRecyclerView()

        notificationAdapter.differ.submitList(uiModel?.notifications)
    }

    private fun setHeaderView(headerContent: LandingNotificationsUi.ContentUi.Header?) {
        binding.headerContentNotification.setContentView(
            headerContent ?: LandingNotificationsUi.ContentUi.Header()
        )
    }

    private fun setupNotificationRecyclerView() {
        with(binding.recyclerViewNotificationList) {
            visibility = View.VISIBLE
            adapter = notificationAdapter
        }
    }

    private fun loading() {
        with(binding) {
            progressCircularLoading.visible()
            recyclerViewNotificationList.gone()
            headerContentNotification.gone()
            errorStateContentError.gone()
        }
    }

    private fun hideLoading() {
        with(binding) {
            progressCircularLoading.gone()
            recyclerViewNotificationList.visible()
            headerContentNotification.visible()
        }
    }

    @SuppressLint("PrivateResource")
    private fun setViewErrorState() {
        with(binding.errorStateContentError) {
            visible()

            setView(
                errorStateUi = ErrorStateUi(
                    alertIcon = android.R.drawable.ic_dialog_alert,
                    message = getString(R.string.my_notification_common_error_offline_message),
                    buttonText = getString(R.string.my_notification_common_try_again_button),
                    onClickedButton = { viewModel.fetchUserId() }
                )
            )
        }
    }

    private fun contentGone() {
        with(binding) {
            recyclerViewNotificationList.gone()
            headerContentNotification.gone()
        }
    }
}