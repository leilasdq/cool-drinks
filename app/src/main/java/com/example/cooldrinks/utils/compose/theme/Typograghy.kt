package com.example.cooldrinks.utils.compose.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.cooldrinks.R

val fonts = FontFamily(
    Font(R.font.serif, weight = FontWeight.Medium),
    Font(R.font.serif_bold, weight = FontWeight.Bold),
    Font(R.font.serif_bold_italic, weight = FontWeight.ExtraBold),
    Font(R.font.serif_italic, weight = FontWeight.Light),
)