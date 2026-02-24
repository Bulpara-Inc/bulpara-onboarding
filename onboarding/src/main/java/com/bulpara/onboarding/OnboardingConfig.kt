package com.bulpara.onboarding

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulpara.paywall.PaywallConfig
import com.bulpara.paywall.TieredPaywallConfig

data class OnboardingConfig(
    val pages: List<OnboardingPage>,
    val branding: OnboardingBranding = OnboardingBranding(),
    val paywallConfig: PaywallConfig? = null,
    val tieredPaywallConfig: TieredPaywallConfig? = null,
)

data class OnboardingPage(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val gradientColors: List<Color>,
)

data class OnboardingBranding(
    val backgroundColor: Color = Color(0xFFFAF8F5),
    val titleColor: Color = Color(0xFF1A1614),
    val subtitleColor: Color = Color(0xFF6B6360),
    val skipTextColor: Color = Color(0xFF9E9490),
    val dotInactiveColor: Color = Color(0xFFD4C8C0),
    val buttonContainerColor: Color = Color.White,
    val buttonContentColor: Color = Color.Unspecified,
)
