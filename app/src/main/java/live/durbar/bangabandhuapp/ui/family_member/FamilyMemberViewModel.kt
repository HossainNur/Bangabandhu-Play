package live.durbar.bangabandhuapp.ui.family_member

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import live.durbar.bangabandhuapp.data.model.family_member.Data
import live.durbar.bangabandhuapp.data.repository.FamilyMemberRepository

class FamilyMemberViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FamilyMemberRepository

    init {
        repository = FamilyMemberRepository(application)
    }

    fun fetchFamilyMemberPhotos(): MutableLiveData<List<Data>> {
        return repository.fetchFamilyMemberPhotos()
    }
}
