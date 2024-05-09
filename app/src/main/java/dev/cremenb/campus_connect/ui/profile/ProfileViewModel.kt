package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.campus_connect.R
import dev.cremenb.data.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    val repository: ProfileRepository
) : ViewModel() {
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
