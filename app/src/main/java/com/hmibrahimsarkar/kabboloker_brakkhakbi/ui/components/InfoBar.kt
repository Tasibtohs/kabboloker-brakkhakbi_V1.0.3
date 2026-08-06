package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.SoftLavender
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun InfoBar(
    leftText: String,
    rightText: String = getBengaliFormattedDateTime()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leftText,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = rightText,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

fun getBengaliFormattedDateTime(timestamp: Long = System.currentTimeMillis()): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("dd MMMM, yyyy • hh:mm a", Locale("bn", "BD"))
    return sdf.format(date)
}

fun getBengaliFullDateTime(timestamp: Long = System.currentTimeMillis()): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("EEEE, dd MMMM, yyyy 'এ' hh:mm a", Locale("bn", "BD"))
    return sdf.format(date)
}
