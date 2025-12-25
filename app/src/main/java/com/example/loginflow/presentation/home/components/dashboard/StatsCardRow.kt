package com.example.loginflow.presentation.home.components.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loginflow.ui.theme.FontSize12
import com.example.loginflow.ui.theme.FontSize18
import com.example.loginflow.ui.theme.Green
import com.example.loginflow.ui.theme.LightGreenBackground
import com.example.loginflow.ui.theme.LightOrangeBackground
import com.example.loginflow.ui.theme.LightRedBackground
import com.example.loginflow.ui.theme.MediumGrayText
import com.example.loginflow.ui.theme.Orange
import com.example.loginflow.ui.theme.Red
import com.example.loginflow.R

@Composable
fun StatsCardsRow(
    availability: String,
    quizAttempts: Int,
    accuracy: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.availability),
            value = availability,
            borderColor = Green,
            backgroundTint = LightGreenBackground,
            iconRes = R.drawable.ic_available
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.quiz),
            value = stringResource(R.string.attempt, quizAttempts),
            borderColor = Orange,
            backgroundTint = LightOrangeBackground,
            iconRes = R.drawable.ic_quiz
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.accuracy),
            value = accuracy,
            borderColor = Red,
            backgroundTint = LightRedBackground,
            iconRes = R.drawable.ic_accuracy
        )

    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    borderColor: Color,
    backgroundTint: Color,
    iconRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundTint
        ),
        border = BorderStroke(1.5.dp, borderColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = borderColor
            )

            Column {
                Text(
                    text = title,
                    fontSize = FontSize12,
                    color = MediumGrayText,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = value,
                    fontSize = FontSize18,
                    fontWeight = FontWeight.Bold,
                    color = borderColor
                )
            }
        }
    }
}