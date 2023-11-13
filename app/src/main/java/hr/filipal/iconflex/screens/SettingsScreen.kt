package hr.filipal.iconflex.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun SettingsScreen() {
    // Definiranje stanja za svaku postavku
    var hideIcon by remember { mutableStateOf(false) }
    var hideTitle by remember { mutableStateOf(false) }
    var showNotification by remember { mutableStateOf(false) }
    var transparency by remember { mutableStateOf(0f) } // Transparentnost od 0 do 1
    var textSize by remember { mutableStateOf(16f) } // Veličina teksta u sp

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Postavke", style = MaterialTheme.typography.titleMedium)

        SettingSwitch("Sakrij ikonu", hideIcon) { hideIcon = it }
        SettingSwitch("Sakrij naziv", hideTitle) { hideTitle = it }
        SettingSwitch("Prikaži obavijesti", showNotification) { showNotification = it }
        SettingSlider("Transparentnost", transparency, 0f, 1f) { transparency = it }
        SettingSlider("Veličina teksta", textSize, 12f, 24f) { textSize = it }
    }
}

@Composable
fun SettingSwitch(title: String, state: Boolean, onStateChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title)
        Spacer(modifier = Modifier.weight(1f))
        Switch(checked = state, onCheckedChange = onStateChange)
    }
}

@Composable
fun SettingSlider(title: String, value: Float, min: Float, max: Float, onValueChange: (Float) -> Unit) {
    Column {
        Text(title)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${value.toInt()}")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Remove, contentDescription = "Smanji", modifier = Modifier.clickable { onValueChange(max(value - 1, min)) })
            Slider(value = value, onValueChange = onValueChange, valueRange = min..max)
            Icon(Icons.Default.Add, contentDescription = "Povećaj", modifier = Modifier.clickable { onValueChange(min(value + 1, max)) })
        }
    }
}

