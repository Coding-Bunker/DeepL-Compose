package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TranslationResultComponent(textResult: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(), elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
        ) {
            SelectionContainer(
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 150.dp)
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            ) {
                Text(text = textResult)
            }
            Spacer(modifier = Modifier.height(4.dp))
            TranslationAction(
                reproduceEnabled = false,
                onReproduceClicked = { /*TODO*/ },
            ) {

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