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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun DrinksList(
    drinks: List<Drink>
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = drinks) { item ->
            DrinkItem(drink = item)
        }
    }
}

@Composable
fun DrinkItem(drink: Drink) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dimen_margin_small)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var timer = 0
        LaunchedEffect(key1 = drink) {
            timer = setTimeToMakeDrink()
        }

        ImageCardBackground {
            Image(
                painter = rememberGlidePainter(drink.imageUrl),
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
            Text(text = "Time to be ready: $timer")
        }
    }
}

fun setTimeToMakeDrink() = Random.nextInt(10, 30)

@Composable @Preview
private fun Prev() {
    val fakeList = mutableListOf<Drink>()
    for (i in 0 .. 5) {
        fakeList.add(Drink("name $i", "https://www.thecocktaildb.com/images/media/drink/xwqvur1468876473.jpg", "$i"))
    }
    DrinksList(fakeList)
}