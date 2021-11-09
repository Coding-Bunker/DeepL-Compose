package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import it.github.samuele794.composedeepl.R
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme

@Composable
fun TranslateHeader() {
    val backgroundColor = MaterialTheme.colors.primary

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        val (translateFrom, switch, translateTo) = createRefs()


        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(translateFrom) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Text(text = "Italiano", color = contentColorFor(backgroundColor))
        }

        Image(
            painter = painterResource(id = R.drawable.ic_swap),
            contentDescription = null,
            modifier = Modifier.constrainAs(switch) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(translateFrom.end)
                end.linkTo(translateTo.start)
            })

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(translateTo) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
        ) {
            Text(text = "Inglese", color = contentColorFor(backgroundColor))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TranslateHeaderPreview() {
    ComposeDeepLTheme {
        TranslateHeader()
    }
}