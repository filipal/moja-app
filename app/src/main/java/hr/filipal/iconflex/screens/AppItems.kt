package hr.filipal.iconflex.screens

import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.ImageBitmap
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.filipal.iconflex.models.App
import hr.filipal.iconflex.viewmodels.AppsViewModel



@Composable
fun AppsListScreen(appsViewModel: AppsViewModel) {
    // Ovdje pretpostavljamo da je appsViewModel već povezan s Composable funkcijom kroz hilt ili neki drugi DI mehanizam
    val appsList = appsViewModel.apps.value ?: listOf()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = appsList, itemContent = { app ->
            AppItem(app = app)
        })
    }
}

@Composable
fun Drawable.toImageBitmap(): ImageBitmap {
    if (this is BitmapDrawable && this.bitmap != null) {
        return this.bitmap.asImageBitmap()
    }

    val bitmap = Bitmap.createBitmap(this.intrinsicWidth, this.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    this.setBounds(0, 0, canvas.width, canvas.height)
    this.draw(canvas)

    return bitmap.asImageBitmap()
}
@Composable
fun AppIcon(app: App) {
    val imageBitmap = app.iconDrawable.toImageBitmap()
    Icon(
        painter = BitmapPainter(imageBitmap),
        contentDescription = app.name,
        modifier = Modifier.size(40.dp)
    )
}
@Composable
fun AppItem(app: App, onItemClick: (App) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(app) }
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIcon(app = app) // Ovdje koristite AppIcon
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = app.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

