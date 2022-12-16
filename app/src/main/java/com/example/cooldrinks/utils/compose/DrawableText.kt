package com.example.cooldrinks.utils.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cooldrinks.utils.compose.theme.iconColor

@Composable
fun DrawableWrapper(
    modifier: Modifier = Modifier,
    @DrawableRes drawableTop: Int? = null,
    @DrawableRes drawableStart: Int? = null,
    @DrawableRes drawableBottom: Int? = null,
    @DrawableRes drawableEnd: Int? = null,
    content: @Composable () -> Unit,
) {
    ConstraintLayout(modifier) {
        val (refImgStart, refImgTop, refImgBottom, refImgEnd, refContent) = createRefs()
        Box(Modifier.constrainAs(refContent) {
            top.linkTo(drawableTop?.let { refImgTop.bottom } ?: parent.top)
            bottom.linkTo(drawableBottom?.let { refImgBottom.top } ?: parent.bottom)
            start.linkTo(drawableStart?.let { refImgStart.end } ?: parent.start)
            end.linkTo(drawableEnd?.let { refImgEnd.start } ?: parent.end)
        }) {
            content()
        }
        drawableTop?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier.constrainAs(refImgTop) {
                    top.linkTo(parent.top)
                    start.linkTo(refContent.start)
                    end.linkTo(refContent.end)
                }
            )
        }
        drawableStart?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier.constrainAs(refImgStart) {
                    top.linkTo(refContent.top)
                    bottom.linkTo(refContent.bottom)
                    start.linkTo(parent.start)
                }
            )
        }
        drawableBottom?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier.constrainAs(refImgBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(refContent.start)
                    end.linkTo(refContent.end)
                }
            )
        }
        drawableEnd?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                Modifier.constrainAs(refImgEnd) {
                    top.linkTo(refContent.top)
                    bottom.linkTo(refContent.bottom)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Composable
fun DrawableWrapper(
    modifier: Modifier = Modifier,
    drawableTop: ImageVector? = null,
    drawableStart: ImageVector? = null,
    drawableBottom: ImageVector? = null,
    drawableEnd: ImageVector? = null,
    topTint: Color? = null,
    startTint: Color? = null,
    bottomTint: Color? = null,
    endTint: Color? = null,
    content: @Composable () -> Unit,
) {
    ConstraintLayout(modifier) {
        val (refImgStart, refImgTop, refImgBottom, refImgEnd, refContent) = createRefs()
        Box(Modifier.constrainAs(refContent) {
            top.linkTo(drawableTop?.let { refImgTop.bottom } ?: parent.top)
            bottom.linkTo(drawableBottom?.let { refImgBottom.top } ?: parent.bottom)
            start.linkTo(drawableStart?.let { refImgStart.end } ?: parent.start)
            end.linkTo(drawableEnd?.let { refImgEnd.start } ?: parent.end)
        }) {
            content()
        }
        drawableTop?.let {
            Icon(
                painter = rememberVectorPainter(it),
                contentDescription = null,
                Modifier
                    .constrainAs(refImgTop) {
                    top.linkTo(parent.top)
                    start.linkTo(refContent.start)
                    end.linkTo(refContent.end)
                },
                tint = topTint ?: iconColor
            )
        }
        drawableStart?.let {
            Icon(
                painter = rememberVectorPainter(it),
                contentDescription = null,
                Modifier.constrainAs(refImgStart) {
                    top.linkTo(refContent.top)
                    bottom.linkTo(refContent.bottom)
                    start.linkTo(parent.start)
                },
                tint = startTint ?: iconColor
            )
        }
        drawableBottom?.let {
            Icon(
                painter = rememberVectorPainter(it),
                contentDescription = null,
                Modifier.constrainAs(refImgBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(refContent.start)
                    end.linkTo(refContent.end)
                },
                tint = bottomTint ?: iconColor
            )
        }
        drawableEnd?.let {
            Icon(
                painter = rememberVectorPainter(it),
                contentDescription = null,
                Modifier.constrainAs(refImgEnd) {
                    top.linkTo(refContent.top)
                    bottom.linkTo(refContent.bottom)
                    end.linkTo(parent.end)
                },
                tint = endTint ?: iconColor
            )
        }
    }
}