package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.common.isGoodMatch
import com.example.domain.person.model.Result
import com.example.ui.R

@Composable
fun PersonItemComposable(
    item: Result,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                Brush.horizontalGradient(listOf(Color.DarkGray, Color.LightGray, Color.DarkGray)),
                RoundedCornerShape(8.dp)
            )
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            item.picture?.large.orEmpty(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(0.75f)
                .height(240.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (
                item.isGoodMatch() == true
            ) {
                Text("⭐")
            }
            Text(text = "${item.name.first.orEmpty()} ${item.name.last.orEmpty()}")
            Text(" | ")
            Text(text = item.dob?.age.toString())
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                {
                    onAccept()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                )
            ) {
                Text(stringResource(R.string.accept), color = Color.Black)
            }
            Button(
                {
                    onReject()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(stringResource(R.string.reject), color = Color.White)
            }
        }
    }
}