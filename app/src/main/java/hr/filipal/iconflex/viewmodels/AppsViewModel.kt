package hr.filipal.iconflex.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.filipal.iconflex.models.App


class AppsViewModel(application: Application) : AndroidViewModel(application) {
    private val _apps = MutableLiveData<List<App>>()
    val apps: LiveData<List<App>> = _apps

    init {
        loadApps()
    }

    private fun loadApps() {
        try {
            val pm = getApplication<Application>().packageManager
            val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA).mapNotNull { appInfo ->
                if (pm.getLaunchIntentForPackage(appInfo.packageName) != null) {
                    val icon = appInfo.loadIcon(pm)
                    App(
                        name = appInfo.loadLabel(pm).toString(),
                        packageName = appInfo.packageName,
                        iconDrawable = icon
                    )
                } else {
                    null
                }
            }
            _apps.postValue(apps)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

// Ažurirajte klasu App kako bi podržavala Drawable ikone
data class App(
    val name: String,
    val packageName: String,
    val iconDrawable: Drawable // Spremanje Drawable ikone
)
