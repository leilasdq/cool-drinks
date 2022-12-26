package com.example.cooldrinks.ui.drinkdetail.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.cooldrinks.R
import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.utils.compose.AppTheme
import com.example.cooldrinks.utils.compose.DrawableWrapper
import com.example.cooldrinks.utils.compose.theme.starColor
import com.example.cooldrinks.utils.compose.theme.timerColor
import kotlin.random.Random

@Composable
fun DetailDrinkView(
    item: Drink,
    onBackButtonClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .fillMaxSize()
    ) {
        val (title, backIcon, card, image, cardContent) = createRefs()
        val guideline = createGuidelineFromTop(fraction = 0.3f)

        Text(
            text = "Drink Info",
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = MaterialTheme.typography.headlineSmall
        )
        Icon(
            Icons.Filled.ArrowBackIos, contentDescription = null,
            modifier = Modifier
                .constrainAs(backIcon) {
                    top.linkTo(parent.top, 8.dp)
                    start.linkTo(parent.start, 8.dp)
                }
                .clickable {
                    onBackButtonClicked.invoke()
                }
        )
        Card(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.dimen_large_radius),
                        topEnd = dimensionResource(id = R.dimen.dimen_large_radius),
                    )
                )
                .constrainAs(card) {

                    top.linkTo(guideline, 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxSize()
        ) {}
        Card(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_margin_small))
                .constrainAs(cardContent) {
                    top.linkTo(image.bottom, 128.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(card.bottom)
                }
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {
            Text(
                text = item.name,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_margin_small))
            ) {
                DrawableWrapper(
                    drawableStart = Icons.Filled.Star,
                    startTint = starColor
                ) {
                    Text(text = String.format( "%.1f", Random.nextDouble(2.0, 5.0)))
                }
                DrawableWrapper(
                    drawableStart = Icons.Filled.Timer,
                    startTint = timerColor
                ) {
                    Text(text = "${Random.nextInt(10, 30)}")
                }
            }
            Text(
                text = "Ingredient list",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_margin_normal)),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            item.ingredientAndMeasure.let {
                val bullet = "\u2022"
                for (ingredientItem in it) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "$bullet ${ingredientItem.key}",
                                modifier = Modifier
                                    .padding(top = dimensionResource(id = R.dimen.dimen_margin_small)),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(modifier = Modifier.padding(start = 4.dp))
                            Text(
                                text = ": ${ingredientItem.value}",
                                modifier = Modifier
                                    .padding(top = dimensionResource(id = R.dimen.dimen_margin_small)),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    }
                }
            }
            Text(
                text = "Instruction",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.dimen_margin_normal)),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            item.instruction?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.dimen_margin_small)),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Image(
            painter = rememberImagePainter(data =(item.imageUrl)),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(128.dp)
                .constrainAs(image) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )

    }
}

@Preview(showSystemUi = true, showBackground = true) @Composable
private fun Prev() {
    AppTheme {
        DetailDrinkView(
            Drink(
                "name",
                "https://www.thecocktaildb.com/images/media/drink/xwqvur1468876473.jpg",
                "1",
                "how to make it",
                ingredientAndMeasure = mapOf("butter" to "5", "milk" to "10")
            ),
            {}
        )
    }
}