package com.example.cooldrinks.ui.drinkslist.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.cooldrinks.R
import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.utils.compose.ImageCardBackground
import com.google.accompanist.glide.rememberGlidePainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import kotlin.random.Random

@Composable
fun DrinksList(
    drinks: List<Drink>,
    onItemClicked: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = drinks) { item ->
            DrinkItem(drink = item, onItemClicked)
        }
    }
}

@Composable
fun DrinkItem(
    drink: Drink,
    onItemClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_margin_small))
            .clickable { onItemClicked.invoke(drink.id.toInt()) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageCardBackground {
            Image(
                painter = rememberImagePainter(data =(drink.imageUrl)),
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_margin_small))
        ) {
            Text(text = drink.name)
            Spacer(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dimen_margin_small))
            )
            Text(text = "Time to be ready: ${setTimeToMakeDrink()} minutes.")
        }
    }
}

fun setTimeToMakeDrink() = Random.nextInt(10, 30)

@Composable @Preview(showSystemUi = true, showBackground = true)
private fun Prev() {
    val fakeList = mutableListOf<Drink>()
    for (i in 0 .. 5) {
        fakeList.add(Drink("name $i", "https://www.thecocktaildb.com/images/media/drink/xwqvur1468876473.jpg", "$i", "hh", null))
    }
    DrinksList(fakeList) {}
}