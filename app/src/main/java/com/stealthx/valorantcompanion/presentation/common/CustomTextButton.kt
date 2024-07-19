package com.stealthx.valorantcompanion.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    title: String,
    onClickPerform: () -> Unit
) {
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            containerColor = ValoWhite,
            contentColor = ValoRed
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            onClickPerform()
        }) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.aeonik)),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}