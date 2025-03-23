package com.example.jetpackcomposeapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme

@Composable
fun OtherField() {
    var number by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("Option 1") }
    val options = listOf("Option 1", "Option 2", "Option 3")

    var isOn by remember { mutableStateOf(false) }

    var sliderValue by remember { mutableStateOf(0f) }

    Column {
        Row {
            Text(text = "Number Field: ")
            TextField(
                value = number,
                onValueChange = { number = it},
                label = {Text("Number")},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "Radio Button: ")
            Column {
                options.forEach{ option ->
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = { selectedOption = option}
                        )

                        Text(option, modifier = Modifier.clickable { selectedOption = option })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Switch: ")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(checked = isOn, onCheckedChange = {isOn = it})
            Text (if (isOn) "On" else "Off", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "Slider: ")
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f
                )

                Text("Value: ${sliderValue.toInt()}")
            }
        }
        var isChecked by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {isChecked = it}
            )
            Text(
                text = "todo",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview( showBackground = true)
@Composable
fun OtherFieldPreview() {
    JetpackComposeAppTheme {
        OtherField()
    }
}