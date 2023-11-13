package hr.filipal.iconflex.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hr.filipal.iconflex.models.App
import kotlinx.coroutines.launch

class AppsViewModel(application: Application) : AndroidViewModel(application) {
    private val _apps = MutableLiveData<List<App>>()
    val apps: LiveData<List<App>> = _apps

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                val pm = getApplication<Application>().packageManager
                val allApps = pm.getInstalledApplications(PackageManager.GET_META_DATA).mapNotNull { appInfo ->
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

                // Filtriranje aplikacija na temelju upita za pretragu
                _apps.postValue(
                    if (_searchQuery.value.isNullOrEmpty()) {
                        allApps
                    } else {
                        allApps.filter { it.name.contains(_searchQuery.value!!, ignoreCase = true) }
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        loadApps()
    }
}

// Ažurirajte klasu App kako bi podržavala Drawable ikone
data class App(
    val name: String,
    val packageName: String,
    val iconDrawable: Drawable // Spremanje Drawable ikone
)
