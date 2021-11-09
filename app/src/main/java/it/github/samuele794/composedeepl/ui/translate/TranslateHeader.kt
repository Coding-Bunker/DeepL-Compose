package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TranslateHeader() {
    val backgroundColor = MaterialTheme.colors.primarySurface
    TopAppBar {

        var isSwaped by remember { mutableStateOf(true) }

        val progress by animateFloatAsState(
            targetValue = if (isSwaped) 0f else 1f,
            animationSpec = tween(500)
        )

        val rotationProgress by animateFloatAsState(
            targetValue = if (isSwaped) 0f else 180f,
            animationSpec = tween(500)
        )

        MotionLayout(
            start = switchConstraintSetActive(),
            end = switchConstraintSetDeActive(),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
//                .background(backgroundColor)
        ) {

            Icon(
                imageVector = Icons.Filled.SwapHoriz,
                contentDescription = null,
//                tint = contentColorFor(backgroundColor = backgroundColor),
                modifier = Modifier
                    .layoutId("switch")
                    .rotate(rotationProgress)
                    .clickable {
                        isSwaped = !isSwaped
                    }
            )

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("translateFrom")
            ) {
                Text(
                    text = "Italiano",
                    color = contentColorFor(backgroundColor)
                )
            }

            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("translateTo"),
            ) {
                Text(
                    text = "Inglese",
                    color = contentColorFor(backgroundColor)
                )
            }
        }
    }
}

private fun switchConstraintSetActive() = ConstraintSet {
    val translateFrom = createRefFor("translateFrom")
    val translateTo = createRefFor("translateTo")
    val switch = createRefFor("switch")


    constrain(translateFrom) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)

    }

    constrain(translateTo) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)

        end.linkTo(parent.end)

    }

    constrain(switch) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(translateFrom.end)
        end.linkTo(translateTo.start)
    }
}

private fun switchConstraintSetDeActive() = ConstraintSet {
    val translateFrom = createRefFor("translateFrom")
    val translateTo = createRefFor("translateTo")
    val switch = createRefFor("switch")


    constrain(translateFrom) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)

        end.linkTo(parent.end)

    }

    constrain(translateTo) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(switch) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

@Preview(showBackground = true)
@Composable
fun TranslateHeaderPreview() {
    ComposeDeepLTheme {
        TranslateHeader()
    }
}