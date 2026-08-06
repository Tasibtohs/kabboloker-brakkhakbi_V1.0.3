package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.font.BengaliFonts
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.AppDisplayFont
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.BottomSheetShape
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldDark
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldGlow
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldLight
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldPrimary
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

val PremiumColorSwatches = listOf(
    "#D4A017", "#1A1A2E", "#8A6CB3", "#2E7D32", "#C62828", "#1565C0",
    "#F4C842", "#4A148C", "#00838F", "#D81B60", "#E65100", "#37474F",
    "#B8860B", "#6A1B9A", "#00695C", "#AD1457", "#EF6C00", "#212121",
    "#C9B3E8", "#283593", "#004D40", "#880E4F", "#BF360C", "#424242"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerSheet(
    sheetState: SheetState,
    initialTitleColorHex: String,
    initialTextColorHex: String,
    onColorsSelected: (titleColorHex: String, textColorHex: String) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Title, 1: Text
    var titleColorHex by remember { mutableStateOf(initialTitleColorHex) }
    var textColorHex by remember { mutableStateOf(initialTextColorHex) }

    val activeColorHex = if (selectedTab == 0) titleColorHex else textColorHex

    // Active color HSV states
    var activeHue by remember { mutableFloatStateOf(0f) }
    var activeSat by remember { mutableFloatStateOf(1f) }
    var activeVal by remember { mutableFloatStateOf(1f) }

    // Sync active color to HSV when tab or color hex changes
    LaunchedEffect(activeColorHex) {
        val parsed = try { Color(android.graphics.Color.parseColor(activeColorHex)) } catch (e: Exception) { GoldPrimary }
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(
            android.graphics.Color.argb(
                255,
                (parsed.red * 255).toInt(),
                (parsed.green * 255).toInt(),
                (parsed.blue * 255).toInt()
            ),
            hsv
        )
        activeHue = hsv[0]
        activeSat = hsv[1]
        activeVal = hsv[2]
    }

    fun updateActiveColor(h: Float, s: Float, v: Float) {
        activeHue = h
        activeSat = s
        activeVal = v
        val hsv = floatArrayOf(h, s, v)
        val argb = android.graphics.Color.HSVToColor(hsv)
        val hex = String.format("#%06X", 0xFFFFFF and argb)
        if (selectedTab == 0) {
            titleColorHex = hex
        } else {
            textColorHex = hex
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = BottomSheetShape,
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Script Header
            Text(
                text = "রঙ নির্বাচন করুন",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = AppDisplayFont,
                color = GoldPrimary,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            // Gold divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(GoldLight, GoldPrimary, GoldDark, Color.Transparent)
                        )
                    )
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Tabs for Title vs Text Color
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                contentColor = GoldPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = GoldPrimary,
                        height = 3.dp
                    )
                }
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = {
                        Text(
                            text = "টাইটেল রঙ",
                            fontSize = 15.sp,
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = {
                        Text(
                            text = "লেখার রঙ",
                            fontSize = 15.sp,
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Live Preview Card
            Text(
                text = "লাইভ প্রিভিউ (লাইটের অনুভূতির ডেমো)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))

            val previewTitleColor = try { Color(android.graphics.Color.parseColor(titleColorHex)) } catch (e: Exception) { GoldPrimary }
            val previewTextColor = try { Color(android.graphics.Color.parseColor(textColorHex)) } catch (e: Exception) { MaterialTheme.colorScheme.onSurface }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldPrimary.copy(alpha = 0.35f), RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "আমার সোনার কবিতা",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = previewTitleColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "যেখানে শব্দরা খেলা করে সোনালি আলোতে, সেখানেই প্রাণ পায় কাব্য...",
                        fontSize = 14.sp,
                        color = previewTextColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 6-Column Swatch Grid
            Text(
                text = "প্রিসেট কালার প্যালেট (৬ কলাম)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.height(150.dp)
            ) {
                items(PremiumColorSwatches) { hex ->
                    val swatchColor = try { Color(android.graphics.Color.parseColor(hex)) } catch (e: Exception) { Color.Gray }
                    val isSelected = hex.equals(activeColorHex, ignoreCase = true)

                    val scaleAnim by animateFloatAsState(
                        targetValue = if (isSelected) 1.18f else 1.0f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        label = "scale"
                    )

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .scale(scaleAnim)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        swatchColor,
                                        swatchColor.copy(alpha = 0.85f)
                                    )
                                )
                            )
                            .border(
                                width = if (isSelected) 2.5.dp else 0.5.dp,
                                color = if (isSelected) GoldPrimary else MaterialTheme.colorScheme.outlineVariant,
                                shape = CircleShape
                            )
                            .clickable {
                                val parsed = try { Color(android.graphics.Color.parseColor(hex)) } catch (e: Exception) { Color.Gray }
                                val hsv = FloatArray(3)
                                android.graphics.Color.colorToHSV(
                                    android.graphics.Color.argb(
                                        255,
                                        (parsed.red * 255).toInt(),
                                        (parsed.green * 255).toInt(),
                                        (parsed.blue * 255).toInt()
                                    ),
                                    hsv
                                )
                                updateActiveColor(hsv[0], hsv[1], hsv[2])
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Custom HSV Color Wheel Section
            Text(
                text = "কাস্টম কালার নির্বাচন (HSV হুইল ও স্লাইডার)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                InteractiveHsvColorWheel(
                    hue = activeHue,
                    saturation = activeSat,
                    onHsvChange = { h, s ->
                        updateActiveColor(h, s, activeVal)
                    },
                    modifier = Modifier.size(160.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Brightness (Value) Slider
            Text(
                text = "উজ্জ্বলতা (Brightness): ${(activeVal * 100).toInt()}%",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Slider(
                value = activeVal,
                onValueChange = { valNew ->
                    updateActiveColor(activeHue, activeSat, valNew)
                },
                valueRange = 0f..1f,
                colors = SliderDefaults.colors(
                    thumbColor = GoldPrimary,
                    activeTrackColor = GoldLight,
                    inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // HEX Input Field with Mini Color Preview Dot
            var hexInputText by remember(activeColorHex) { mutableStateOf(activeColorHex) }

            OutlinedTextField(
                value = hexInputText,
                onValueChange = { input ->
                    hexInputText = input
                    if (input.startsWith("#") && (input.length == 7 || input.length == 9)) {
                        try {
                            val parsed = Color(android.graphics.Color.parseColor(input))
                            val hsv = FloatArray(3)
                            android.graphics.Color.colorToHSV(
                                android.graphics.Color.argb(
                                    255,
                                    (parsed.red * 255).toInt(),
                                    (parsed.green * 255).toInt(),
                                    (parsed.blue * 255).toInt()
                                ),
                                hsv
                            )
                            updateActiveColor(hsv[0], hsv[1], hsv[2])
                        } catch (_: Exception) {}
                    }
                },
                label = { Text("HEX কোড") },
                singleLine = true,
                leadingIcon = {
                    val activeColor = try { Color(android.graphics.Color.parseColor(activeColorHex)) } catch (e: Exception) { Color.Gray }
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(activeColor)
                            .border(1.dp, GoldPrimary, CircleShape)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(26.dp))

            // Golden Gradient "সম্পন্ন" Button
            Button(
                onClick = {
                    onColorsSelected(titleColorHex, textColorHex)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(14.dp), spotColor = GoldGlow),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(GoldLight, GoldPrimary, GoldDark)
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "সম্পন্ন",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun InteractiveHsvColorWheel(
    hue: Float,
    saturation: Float,
    onHsvChange: (hue: Float, saturation: Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val sizeMin = min(size.width, size.height)
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val radius = sizeMin / 2f
                    val dx = offset.x - center.x
                    val dy = offset.y - center.y
                    val dist = sqrt(dx * dx + dy * dy)
                    val sat = (dist / radius).coerceIn(0f, 1f)

                    var angleRad = atan2(dy, dx)
                    if (angleRad < 0) angleRad += (2 * Math.PI).toFloat()
                    val h = (Math.toDegrees(angleRad.toDouble())).toFloat()

                    onHsvChange(h, sat)
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val sizeMin = min(size.width, size.height)
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val radius = sizeMin / 2f
                    val pos = change.position
                    val dx = pos.x - center.x
                    val dy = pos.y - center.y
                    val dist = sqrt(dx * dx + dy * dy)
                    val sat = (dist / radius).coerceIn(0f, 1f)

                    var angleRad = atan2(dy, dx)
                    if (angleRad < 0) angleRad += (2 * Math.PI).toFloat()
                    val h = (Math.toDegrees(angleRad.toDouble())).toFloat()

                    onHsvChange(h, sat)
                }
            }
    ) {
        val sizeMin = min(size.width, size.height)
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = sizeMin / 2f

        // Draw radial hue-saturation circle
        for (r in 0..radius.toInt() step 3) {
            val sat = r.toFloat() / radius
            val colors = (0..360 step 15).map { angle ->
                Color.hsv(angle.toFloat(), sat, 1f)
            }
            drawCircle(
                brush = Brush.sweepGradient(colors = colors, center = center),
                radius = r.toFloat(),
                center = center
            )
        }

        // Draw outer ring
        drawCircle(
            color = GoldPrimary,
            radius = radius,
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw selected thumb position
        val thumbAngleRad = Math.toRadians(hue.toDouble())
        val thumbDist = saturation * radius
        val thumbX = center.x + thumbDist * cos(thumbAngleRad).toFloat()
        val thumbY = center.y + thumbDist * sin(thumbAngleRad).toFloat()

        drawCircle(
            color = Color.White,
            radius = 10.dp.toPx(),
            center = Offset(thumbX, thumbY)
        )
        drawCircle(
            color = GoldPrimary,
            radius = 10.dp.toPx(),
            center = Offset(thumbX, thumbY),
            style = Stroke(width = 3.dp.toPx())
        )
    }
}
