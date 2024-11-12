package dev.cremenb.campus_connect.ui.eventComment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Comment
import dev.cremenb.api.models.CommentData
import dev.cremenb.data.CommentsRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventCommentViewModel @Inject constructor (
    private val repository: CommentsRepository,
) : ViewModel() {

    var allComments: List<Comment>? = null
}