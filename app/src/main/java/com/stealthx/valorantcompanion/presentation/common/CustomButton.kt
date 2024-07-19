package com.stealthx.valorantcompanion.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoRed

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    onClickPerform: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ValoRed,
            contentColor = ValoBlack
        ),
        onClick = {
        onClickPerform()
    }) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.aeonik)),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}