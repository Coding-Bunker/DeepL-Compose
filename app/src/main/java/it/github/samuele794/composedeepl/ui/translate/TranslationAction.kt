package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
inline fun TranslationAction(
    reproduceEnabled: Boolean,
    noinline onReproduceClicked: () -> Unit,
    crossinline actions: @Composable RowScope.() -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val (sound, actionsId) = createRefs()

        IconButton(
            onClick = onReproduceClicked,
            enabled = reproduceEnabled,
            modifier = Modifier
                .constrainAs(sound) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = null)
        }

        Row(
            modifier = Modifier.constrainAs(actionsId) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(sound.end)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            horizontalArrangement = Arrangement.End,
            content = actions
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TranslateActionPreview() {
    TranslationAction(reproduceEnabled = false, onReproduceClicked = {

    }) {

    }
}