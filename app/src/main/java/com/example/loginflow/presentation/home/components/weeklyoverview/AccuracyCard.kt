package com.example.loginflow.presentation.home.components.weeklyoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflow.R
import com.example.loginflow.data.StudentInfoDTO

@Composable
fun AccuracyCard(
    overallAccuracy: StudentInfoDTO.WeeklyOverview.OverallAccuracy,
    modifier: Modifier = Modifier
) {
    val progress = (overallAccuracy.percentage / 100f).coerceIn(0f, 1f)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AccuracyHeader()

            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            AccuracyText(percentage = overallAccuracy.percentage)

            Spacer(modifier = Modifier.height(10.dp))

            AccuracyProgress(progress = progress)
        }
    }
}

@Composable
private fun AccuracyHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.accuracy),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_accuracy_weekly),
            contentDescription = null,
            modifier = Modifier.size(36.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun AccuracyText(
    percentage: Int
) {
    Text(
        text = stringResource(R.string.percent_correct, percentage),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

@Composable
private fun AccuracyProgress(
    progress: Float
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp)),
        color = Color(0xFFF26C6C),
        trackColor = Color(0xFFFDECEC),
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    )
}