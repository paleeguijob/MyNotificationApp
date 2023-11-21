package aof.rp.mynotificationapp.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            progressCircularLoading.visibility = View.VISIBLE
            recyclerViewNotificationList.visibility = View.GONE
            headerContentNotification.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        with(binding) {
            progressCircularLoading.visibility = View.GONE
            recyclerViewNotificationList.visibility = View.VISIBLE
            headerContentNotification.visibility = View.VISIBLE
        }
    }

    private fun setViewErrorState() {
        with(binding.viewErrorState) {
            root.visibility = View.VISIBLE

            buttonTryAgain.setOnClickListener {
                viewModel.fetchUserId()
            }
        }
    }
}