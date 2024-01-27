package com.durbar.bangabandhuplay.ui.pathshala

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.durbar.bangabandhuplay.data.model.pathshala.DataX
import com.durbar.bangabandhuplay.data.model.pathshala.Ebook
import com.durbar.bangabandhuplay.data.repository.PathshalaRepository
import com.durbar.bangabandhuplay.data.repository.PathshalaRepositoryJava

class PathshalaViewModel(application: Application) : AndroidViewModel(application) {

      private val repository: PathshalaRepositoryJava = PathshalaRepositoryJava(application)

      fun fetchPdfs(): MutableLiveData<List<DataX>?> {
          return repository.fetchEbook()
      }
}