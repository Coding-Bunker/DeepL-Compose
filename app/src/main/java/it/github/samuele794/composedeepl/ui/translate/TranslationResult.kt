package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TranslationResultComponent(textResult: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(), elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            SelectionContainer(
                Modifier
                    .defaultMinSize(minHeight = 150.dp)
            ) {
                Text(text = textResult)
            }
            Spacer(modifier = Modifier.height(4.dp))
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (sound, microphone, photo, document) = createRefs()

                Icon(
                    imageVector = Icons.Filled.VolumeUp, contentDescription = "",
                    modifier = Modifier.constrainAs(sound) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                Icon(
                    imageVector = Icons.Outlined.Mic,
                    contentDescription = "",
                    modifier = Modifier.constrainAs(microphone) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TranslationResultPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TranslationResultComponent(textResult = "Test come va")
    }
}