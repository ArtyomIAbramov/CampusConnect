package dev.cremenb.campus_connect.ui.eventComment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.api.models.Comment
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.databinding.FragmentEventCommentBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.VerticalSpaceItemDecoration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class EventCommentFragment : Fragment() {

    private var _binding : FragmentEventCommentBinding? = null

    private val binding get() = _binding!!

    private lateinit var commentRecyclerView: RecyclerView
    private lateinit var messageAdapter: CommentAdapter

    private val viewModel: EventCommentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventCommentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as MainActivity).navView.visibility = View.GONE

        initView()

        val comments = mutableListOf(
            Comment(
                text = "Круто мероприятие",
                createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:30"),
                login = "user1"
            ),
            Comment(
                text = "Все отлично, спасибо!",
                createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:35"),
                login = "user2"
            ),
            Comment(
                text = "Всё понравилось \uD83D\uDE04",
                createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:40"),
                login = "user1"
            ),
        )

        viewModel.allComments = comments
        updateRecyclerView()

        return root
    }

    private fun updateRecyclerView() {
        messageAdapter = CommentAdapter(viewModel.allComments!!)
        commentRecyclerView.adapter = messageAdapter

        commentRecyclerView.post {
            commentRecyclerView.scrollToPosition(viewModel.allComments!!.size - 1)
        }
    }

    private fun initView()
    {
        val messageEditText = binding.messageEditText
        val sendButton = binding.sendButton
        val titleEventName = binding.titleEventName

        commentRecyclerView = binding.messageRecyclerView
        commentRecyclerView.addItemDecoration(VerticalSpaceItemDecoration(20))
        commentRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        titleEventName.text = arguments?.getString("eventName")

        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()
            if (message.isNotEmpty()) {

                val comments = mutableListOf(
                    Comment(
                        text = "Круто мероприятие",
                        createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:30"),
                        login = "user1"
                    ),
                    Comment(
                        text = "Все отлично, спасибо!",
                        createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:35"),
                        login = "user2"
                    ),
                    Comment(
                        text = "Всё понравилось \uD83D\uDE04",
                        createdAt = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).parse("15.03.2023 10:40"),
                        login = "user1"
                    ),

                    Comment(
                        text = message,
                        createdAt = Date(),
                        login = "user1"
                    ),
                )
                viewModel.allComments = comments
                updateRecyclerView()

                messageEditText.text.clear()
            }
        }
    }
}