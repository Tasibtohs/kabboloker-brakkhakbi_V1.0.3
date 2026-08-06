package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hmibrahimsarkar.kabboloker_brakkhakbi.R
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.DarkBackground
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldDark
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldGlow
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldLight
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldPrimary
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.SoftLavender
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.font.BengaliFonts

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    // Custom stylish offline font for title with fallback
    val galadaFont = BengaliFonts.getFontByKey("anupam_mahdi").fontFamily
    val tiroFont = BengaliFonts.getFontByKey("alinur_ichamati").fontFamily

    // Animations
    val logoScale = remember { Animatable(0.2f) }
    val logoAlpha = remember { Animatable(0f) }
    val logoRotationY = remember { Animatable(-90f) }

    val titleOffsetY = remember { Animatable(50f) }
    val titleAlpha = remember { Animatable(0f) }

    val subtitleAlpha = remember { Animatable(0f) }
    val progressVal = remember { Animatable(0f) }

    // Infinite ambient animations
    val infiniteTransition = rememberInfiniteTransition(label = "ambient")
    
    val outerRingRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000, easing = LinearEasing)
        ),
        label = "outerRing"
    )

    val innerRingRotation by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing)
        ),
        label = "innerRing"
    )

    val auraPulse by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "auraPulse"
    )

    val particlePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing)
        ),
        label = "particles"
    )

    val shimmerX by infiniteTransition.animateFloat(
        initialValue = -0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing)
        ),
        label = "shimmer"
    )

    LaunchedEffect(Unit) {
        // Step 1: 3D Flip & Bouncy Scale Logo Reveal
        launch {
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = 0.55f,
                    stiffness = 280f
                )
            )
        }
        launch {
            logoAlpha.animateTo(1f, animationSpec = tween(500))
        }
        launch {
            logoRotationY.animateTo(0f, animationSpec = tween(800, easing = FastOutSlowInEasing))
        }

        delay(400)
        // Step 2: Title float up & fade
        launch {
            titleOffsetY.animateTo(0f, animationSpec = tween(650, easing = FastOutSlowInEasing))
        }
        launch {
            titleAlpha.animateTo(1f, animationSpec = tween(650))
        }

        delay(300)
        // Step 3: Subtitle poetry tag line
        launch {
            subtitleAlpha.animateTo(1f, animationSpec = tween(500))
        }

        // Step 4: Smooth progress fill
        launch {
            progressVal.animateTo(1f, animationSpec = tween(1500, easing = FastOutSlowInEasing))
        }

        delay(1800)
        onSplashFinished()
    }

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            DarkBackground,
            Color(0xFF0D111A),
            Color(0xFF161224),
            Color(0xFF090D14)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        // Floating Golden Ambient Dust Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            for (i in 0..15) {
                val factor = (i * 1.3f)
                val x = (canvasWidth * 0.1f + (canvasWidth * 0.8f * ((i * 17) % 10) / 10f)) +
                        sin(particlePhase + factor) * 30f
                val y = (canvasHeight * 0.15f + (canvasHeight * 0.7f * ((i * 13) % 10) / 10f)) +
                        cos(particlePhase + factor) * 20f
                val radius = (3f + (i % 4) * 1.5f)
                val alpha = (0.2f + 0.3f * sin(particlePhase + factor)).coerceIn(0.1f, 0.7f)

                drawCircle(
                    color = GoldLight.copy(alpha = alpha),
                    radius = radius,
                    center = Offset(x, y)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // Central Luxury Logo with 3D animation & Dual Orbiting Rings
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(220.dp)
            ) {
                // Outer Orbiting Ring with gradient stroke
                Box(
                    modifier = Modifier
                        .size(210.dp)
                        .scale(auraPulse)
                        .rotate(outerRingRotation)
                        .border(
                            width = 2.dp,
                            brush = Brush.sweepGradient(
                                listOf(
                                    GoldLight.copy(alpha = 0.8f),
                                    Color.Transparent,
                                    SoftLavender.copy(alpha = 0.5f),
                                    GoldGlow.copy(alpha = 0.9f)
                                )
                            ),
                            shape = CircleShape
                        )
                )

                // Middle Dashed Orbiting Ring rotating counter-clockwise
                Canvas(
                    modifier = Modifier
                        .size(175.dp)
                        .rotate(innerRingRotation)
                ) {
                    drawCircle(
                        brush = Brush.sweepGradient(
                            listOf(
                                GoldPrimary.copy(alpha = 0.6f),
                                Color.Transparent,
                                GoldLight.copy(alpha = 0.8f)
                            )
                        ),
                        style = Stroke(
                            width = 1.5f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
                        )
                    )
                }

                // Inner Radiant Glowing Background Aura
                Box(
                    modifier = Modifier
                        .size(135.dp)
                        .scale(logoScale.value)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    GoldGlow.copy(alpha = 0.35f),
                                    GoldLight.copy(alpha = 0.15f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                // Stylish App Logo with 3D Flip graphics layer
                Box(
                    modifier = Modifier
                        .size(125.dp)
                        .scale(logoScale.value)
                        .alpha(logoAlpha.value)
                        .graphicsLayer {
                            rotationY = logoRotationY.value
                            cameraDistance = 12 * density
                        }
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(GoldLight, GoldDark, GoldPrimary)
                            ),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.poetry_app_logo_1785492330798),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Dynamic Gold Shimmer Brush for Title
            val goldTextBrush = Brush.horizontalGradient(
                colors = listOf(
                    GoldLight,
                    GoldPrimary,
                    GoldGlow,
                    GoldLight,
                    GoldDark
                ),
                startX = shimmerX * 1000f,
                endX = (shimmerX + 0.8f) * 1000f
            )

            // App Main Title in Galada Font
            Text(
                text = "কাব্যলোকের ব্রক্ষকবি",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = galadaFont,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium.copy(
                    brush = goldTextBrush
                ),
                modifier = Modifier
                    .graphicsLayer {
                        translationY = titleOffsetY.value
                    }
                    .alpha(titleAlpha.value)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle Line with Poetic Touch
            Text(
                text = "শব্দে ও ছন্দে সাহিত্যের অনবদ্য আসর",
                fontSize = 17.sp,
                fontFamily = tiroFont,
                color = SoftLavender,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(subtitleAlpha.value)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Custom Glassmorphic Progress Bar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(200.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFF2A2238))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progressVal.value)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(GoldDark, GoldPrimary, GoldLight)
                                )
                            )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Pulsing Progress Dots
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(GoldLight)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(GoldPrimary)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(SoftLavender)
                    )
                }
            }
        }
    }
}

