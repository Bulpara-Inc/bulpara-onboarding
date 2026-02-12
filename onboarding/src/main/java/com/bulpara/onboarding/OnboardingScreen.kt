package com.bulpara.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bulpara.paywall.BillingManager
import com.bulpara.paywall.PaywallEntryPoint
import com.bulpara.paywall.PaywallScreen
import com.bulpara.paywall.PaywallViewModel
import com.bulpara.onboarding.internal.PageIndicator
import com.bulpara.onboarding.internal.Spacing
import com.bulpara.onboarding.internal.ValuePage
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    config: OnboardingConfig,
    billingManager: BillingManager,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val totalPages = config.pages.size + 1 // value pages + paywall
    val pagerState = rememberPagerState(pageCount = { totalPages })
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        userScrollEnabled = true,
    ) { pageIndex ->
        if (pageIndex < config.pages.size) {
            val page = config.pages[pageIndex]
            val accentColor = page.gradientColors.firstOrNull() ?: Color(0xFF6200EE)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(config.branding.backgroundColor),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Value page content (gradient + icon + title + subtitle)
                ValuePage(
                    page = page,
                    branding = config.branding,
                )

                // Push controls to bottom
                Spacer(modifier = Modifier.weight(1f))

                // Controls
                PageIndicator(
                    pageCount = config.pages.size,
                    currentPage = pageIndex,
                    activeColor = accentColor,
                    inactiveColor = config.branding.dotInactiveColor,
                )

                Spacer(modifier = Modifier.height(Spacing.lg))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pageIndex + 1)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = Spacing.xl),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = accentColor,
                    ),
                    shape = MaterialTheme.shapes.medium,
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp,
                    ),
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))
            }
        } else {
            // Paywall page (last page)
            val paywallViewModel = viewModel<PaywallViewModel>(
                factory = PaywallEntryPoint.createViewModelFactory(
                    billingManager = billingManager,
                    config = config.paywallConfig,
                ),
            )
            PaywallScreen(
                config = config.paywallConfig,
                viewModel = paywallViewModel,
                onDismiss = onComplete,
            )
        }
    }
}
