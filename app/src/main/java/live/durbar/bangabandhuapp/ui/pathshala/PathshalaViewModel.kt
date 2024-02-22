package live.durbar.bangabandhuapp.ui.pathshala

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.pathshala.PathshalaResponse
import live.durbar.bangabandhuapp.data.repository.PathshalaRepositoryJava

class PathshalaViewModel(application: Application) : AndroidViewModel(application) {

      private val repository: PathshalaRepositoryJava = PathshalaRepositoryJava(application)

      fun fetchPdfs(): MutableLiveData<PathshalaResponse?> {
          return repository.fetchEbook()
      }
}