package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import it.github.samuele794.composedeepl.R

@Composable
fun TranslationComponent(translationText: String, onTranslationChanged: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 2.dp) {
        Column {
            EditableClearComponent(translationText, onTranslationChanged)
        }
    }
}

@Composable
fun EditableClearComponent(translationText: String, valueChanged: (String) -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (textRef, clearRef) = createRefs()

        TextField(
            modifier = Modifier
                .constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(clearRef.start)
                    width = Dimension.fillToConstraints
                }
                .defaultMinSize(minHeight = 150.dp),
            value = translationText,
            placeholder = { Text(text = stringResource(R.string.enter_text_hint)) },
            onValueChange = {
                valueChanged.invoke(it)
            },
            colors = textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        if (translationText.isNotEmpty()) {
            IconButton(
                modifier = Modifier.constrainAs(clearRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                onClick = { valueChanged.invoke("") }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TranslationComponentPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TranslationComponent("Test") {

        }
    }
}