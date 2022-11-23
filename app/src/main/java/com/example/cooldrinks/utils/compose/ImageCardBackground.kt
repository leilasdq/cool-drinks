package com.example.cooldrinks.utils.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.cooldrinks.R

@Composable
fun ImageCardBackground(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_main_radius)),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.secondary_brand_color))
    ) {
        Column(content = content)
    }
}