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
import dev.cremenb.api.models.Event
import dev.cremenb.api.models.EventStatus
import dev.cremenb.api.models.Place
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentEventsBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.EdgeItemDecoration
import dev.cremenb.utilities.VerticalSpaceItemDecoration
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventCatalogAdapter

    private var _binding : FragmentEventsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: EventsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val events = mutableListOf(
            Event(
                id = "event1",
                name = "Концерт классической музыки",
                thumbnail = "https://susanintop.com/wp-content/uploads/2018/12/130-11.jpg",
                description = "Вечер классической музыки в Большом зале консерватории",
                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("25.11.2024"),
                place = Place(
                    id = 0,
                    name = "Большой зал консерватории",
                    adress = "ул. Большая Дмитровка, 1"
                ),
                status = EventStatus(
                    id = 2,
                    name = "Активен"
                ),
                users = null,
                isParticipant = false
            ),
            Event(
                id = "event2",
                name = "Выставка современного искусства",
                thumbnail = "https://static.tildacdn.com/tild6165-3431-4133-a538-613336663132/A_T_1053.jpg",
                description = "Выставка работ молодых художников",
                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("15.12.2024"),
                place = Place(
                    id = 1,
                    name = "Галерея современного искусства",
                    adress = "ул. Тверская, 10"
                ),
                status = EventStatus(
                    id = 1,
                    name = "Активен"
                ),
                users = null,
                isParticipant = false
            ),
            Event(
                id = "event3",
                name = "Театральная премьера",
                thumbnail = "https://old.mospuppets.ru/wp-content/uploads/2019/05/petr-02002.jpg",
                description = "Премьера новой пьесы в Театре на Малой Бронной",
                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("01.05.2025"),
                place = Place(
                    id = 3,
                    name = "Театр на Малой Бронной",
                    adress = "ул. Малая Бронная, 1"
                ),
                status = EventStatus(
                    id = 1,
                    name = "Активен"
                ),
                users = null,
                isParticipant = true
            ),
            Event(
                id = "event4",
                name = "Фестиваль скейтбордистов",
                thumbnail = "https://srednyadm.ru/media/resized/1xj6EAO8kgWZywH_5vGLXg7rEjp_m9oXxrwK__EPpRM/rs:fit:1024:768/aHR0cHM6Ly9zcmVk/bnlhZG0ucnUvbWVk/aWEvcHJvamVjdF9t/b18zOTEvN2EvMjYv/OGUvMDAvODEvZjYv/aW1hZ2UwMDEuanBn.jpg",
                description = "Фестиваль скейтбордистов вместе с профессионалами",
                date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse("10.05.2025"),
                place = Place(
                    id = 4,
                    name = "Парк Горького",
                    adress = "ул. Крымский Вал, 9"
                ),
                status = EventStatus(
                    id = 3,
                    name = "Активен"
                ),
                users = null,
                isParticipant = true
            ),
        )

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonsClickListener()

        eventRecyclerView = binding.recyclerView
        eventRecyclerView.addItemDecoration(EdgeItemDecoration(10))
        eventRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.allEvents = events
        setAdapter()

        return root
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).navView.visibility = View.VISIBLE
    }

    private fun setAdapter()
    {
        eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.allEvents!!, viewModel::takePart, findNavController())
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