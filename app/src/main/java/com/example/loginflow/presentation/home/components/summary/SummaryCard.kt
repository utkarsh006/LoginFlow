package com.example.loginflow.presentation.home.components.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loginflow.ui.theme.Black
import com.example.loginflow.ui.theme.DarkGrayText
import com.example.loginflow.ui.theme.FontSize14
import com.example.loginflow.ui.theme.FontSize20
import com.example.loginflow.ui.theme.FontSize22
import com.example.loginflow.ui.theme.LightPurpleBackground
import com.example.loginflow.ui.theme.PurpleBorder
import com.example.loginflow.ui.theme.PurpleText
import com.example.loginflow.ui.theme.White
import com.example.loginflow.R

@Composable
fun TodaySummaryCard(
    mood: String,
    description: String,
    videoAction: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.todays_summary),
            fontSize = FontSize20,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightPurpleBackground
            ),
            border = BorderStroke(1.dp, PurpleBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Character
                Icon(
                    painter = painterResource(id = R.drawable.ic_focused),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Mood
                Text(
                    text = mood,
                    fontSize = FontSize22,
                    fontWeight = FontWeight.Bold,
                    color = PurpleText
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = description,
                    fontSize = FontSize14,
                    color = DarkGrayText,
                    textAlign = TextAlign.Center,
                    lineHeight = FontSize20
                )

                Spacer(modifier = Modifier.height(20.dp))

                // CTA Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Black)
                        .clickable { }
                        .padding(vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = White
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = videoAction,
                        fontSize = FontSize14,
                        color = White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}