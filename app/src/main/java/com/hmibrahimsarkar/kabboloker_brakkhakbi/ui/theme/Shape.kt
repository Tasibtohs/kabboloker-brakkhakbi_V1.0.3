package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),      // Buttons: 12-14dp
    medium = RoundedCornerShape(16.dp),     // Cards: 16dp
    large = RoundedCornerShape(24.dp),      // Bottom sheets/Drawer: 24-28dp
    extraLarge = RoundedCornerShape(28.dp)
)

val PillShape = RoundedCornerShape(50)
val DrawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
val BottomSheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
