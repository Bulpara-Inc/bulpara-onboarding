package com.bulpara.onboarding.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulpara.onboarding.OnboardingBranding
import com.bulpara.onboarding.OnboardingPage

@Composable
internal fun ValuePage(
    page: OnboardingPage,
    branding: OnboardingBranding,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(branding.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Top: gradient + icon circle (fixed 45% of screen)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(Brush.linearGradient(page.gradientColors)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(IconCircle.size)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = page.icon,
                    contentDescription = null,
                    modifier = Modifier.size(IconCircle.iconSize),
                    tint = Color.White.copy(alpha = 0.9f),
                )
            }
        }

        // Bottom: title + subtitle (remaining 55%)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f)
                .padding(horizontal = Spacing.xl)
                .padding(top = Spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = branding.titleColor,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(Spacing.md))

            Text(
                text = page.subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = branding.subtitleColor,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
            )
        }
    }
}
