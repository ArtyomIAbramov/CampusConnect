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
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.databinding.FragmentEventCommentBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.VerticalSpaceItemDecoration

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

        viewModel.getComments(arguments?.getString("eventId")!!)
        (activity as MainActivity).navView.visibility = View.GONE

        initView()

        viewModel.getCommentResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allComments = result.data
                    updateRecyclerView()
                }
                is RequestResult.Error -> {
                    // Обработка ошибки
                }
                is RequestResult.Exception -> {
                    // Обработка исключения
                }
                is RequestResult.InProgress -> {
                    // Обработка состояния в процессе
                }
                // Другие возможные состояния
            }
        }

        viewModel.postCommentsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allComments = result.data
                    updateRecyclerView()
                }
                is RequestResult.Error -> {
                    // Обработка ошибки
                }
                is RequestResult.Exception -> {
                    // Обработка исключения
                }
                is RequestResult.InProgress -> {
                    // Обработка состояния в процессе
                }
                // Другие возможные состояния
            }
        }

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
                viewModel.postComment(arguments?.getString("eventId")!!, message)
                messageEditText.text.clear()
            }
        }
    }
}