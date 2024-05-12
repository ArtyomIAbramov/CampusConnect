package dev.cremenb.campus_connect.ui.events

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentEventsBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.EdgeItemDecoration
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
        eventRecyclerView.addItemDecoration(EdgeItemDecoration(10))
        eventRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.eventsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allEvents = result.data
                    setAdapter()
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

    private fun setAdapter()
    {
        eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.allEvents!!, viewModel::takePart)
        eventRecyclerView.adapter = eventAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { eventAdapter.filter(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { eventAdapter.filter(it) }
                return false
            }
        })
    }


    private fun setButtonsClickListener()
    {
        binding.buttonAll.setOnClickListener {
            eventAdapter.updateData(4)
        }

        binding.buttonSport.setOnClickListener {
            eventAdapter.updateData(3)
        }

        binding.buttonStudy.setOnClickListener {
            eventAdapter.updateData(1)
        }

        binding.buttonEntertainment.setOnClickListener {
            eventAdapter.updateData(2)
        }
    }
}