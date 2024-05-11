package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Profile
import dev.cremenb.campus_connect.R
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    val repository: ProfileRepository
) : ViewModel() {

    var profileResult = MutableLiveData<RequestResult<Profile>>()

    fun getProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getProfile()
                profileResult.postValue(response)
            }
        }
    }

    fun getEvents() : List<MyData>{
        val dataList = ArrayList<MyData>()
        dataList.add(MyData("Второй номер на вашей SIM", R.drawable.ic_notifications_black_24dp))
        dataList.add(MyData("Переадресация вызовов", R.drawable.ic_notifications_black_24dp))
        dataList.add(MyData("Переадресация SMS", R.drawable.ic_notifications_black_24dp))
        dataList.add(MyData("Карточка для примера", R.drawable.ic_notifications_black_24dp))
        dataList.add(MyData("Еще одна карточка для примера", R.drawable.ic_notifications_black_24dp))
        dataList.add(MyData("Последняя карточка для примера", R.drawable.ic_notifications_black_24dp))
        return dataList
    }
}
