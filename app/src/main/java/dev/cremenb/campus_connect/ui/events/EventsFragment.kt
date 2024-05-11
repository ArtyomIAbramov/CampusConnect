package dev.cremenb.campus_connect.ui.events

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentEventsBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.VerticalSpaceItemDecoration

@AndroidEntryPoint
class EventsFragment : Fragment() {

    companion object {
        fun newInstance() = EventsFragment()
    }

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventCatalogAdapter

    private var _binding : FragmentEventsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: EventsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getEvents()

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonsClickListener()

        eventRecyclerView = binding.recyclerView
        val spaceDecoration = VerticalSpaceItemDecoration(10, 10)
        eventRecyclerView.addItemDecoration(spaceDecoration)
        eventRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.eventsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allEvents = result.data
                    eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.allEvents!!)
                    eventRecyclerView.adapter = eventAdapter
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

    private fun setButtonsClickListener()
    {
        binding.buttonSport.setOnClickListener {
            val filteredEvents = viewModel.allEvents?.filter { it.status?.id == 3 }
            eventAdapter = EventCatalogAdapter(requireActivity(), filteredEvents!!)
            eventRecyclerView.adapter = eventAdapter
        }

        binding.buttonStudy.setOnClickListener {
            val filteredEvents = viewModel.allEvents?.filter { it.status?.id == 1 }
            eventAdapter = EventCatalogAdapter(requireActivity(), filteredEvents!!)
            eventRecyclerView.adapter = eventAdapter
        }

        binding.buttonEntertainment.setOnClickListener {
            val filteredEvents = viewModel.allEvents?.filter { it.status?.id == 2 }
            eventAdapter = EventCatalogAdapter(requireActivity(), filteredEvents!!)
            eventRecyclerView.adapter = eventAdapter
        }
    }
}