package dev.cremenb.campus_connect.ui.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import EventAdapter
import androidx.lifecycle.viewModelScope
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        eventRecyclerView = root.findViewById(R.id.event_recycler_view)
        eventRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        eventAdapter = EventAdapter(requireActivity(), viewModel.getEvents())
        eventRecyclerView.adapter = eventAdapter

        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val response = viewModel.repository.getProfile()
                withContext(Dispatchers.Main)
                {
                    when (response){
                        is RequestResult.Success -> {
                        }
                        is RequestResult.Error -> {
                        }

                        is RequestResult.Exception -> {
                        }
                        is RequestResult.InProgress ->
                        {

                        }
                    }
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
