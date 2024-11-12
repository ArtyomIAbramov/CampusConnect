package dev.cremenb.campus_connect.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.api.models.Event
import dev.cremenb.api.models.Gender
import dev.cremenb.api.models.Place
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.University
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import dev.cremenb.campus_connect.ui.events.EventCatalogAdapter
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.HorizontalSpaceItemDecoration
import dev.cremenb.utilities.VerticalSpaceItemDecoration
import java.util.Date

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventCatalogAdapter

    private lateinit var optionsRecyclerView: RecyclerView
    private lateinit var optionsAdapter: OptionsAdapter

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 100
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonsClickListener()

        eventRecyclerView = binding.eventRecyclerView
        eventRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(30))
        eventRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        optionsRecyclerView = binding.optionsRecyclerView
        optionsRecyclerView.addItemDecoration(VerticalSpaceItemDecoration(30))
        optionsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        setOptionAdapter()


        val profile = Profile(
            id = "12345",
            name = "Лев",
            surname = "Ксенофонтов",
            login = "ksone",
            gender = Gender(id = 1, name = "Мужской"),
            phone = "+79991234567",
            email = "ksone@example.com",
            token = "your_token_here",
            university = University(
                id = 1,
                name = "ИГЭУ",
                initials = "ИГЭУ",
                logo = null,
                description = null,
            ),
            events = mutableListOf(
                Event(
                    id = "event1",
                    name = "Концерт",
                    thumbnail = "https://static.re-store.ru/upload/static/re/yamaha-tw-e3a/01@3x.jpg",
                    description = "Концерт классической музыки",
                    date = Date(),
                    place = Place(
                        name = "Б231",
                    ),
                    status = null,
                    users = null,
                    isParticipant = false
                ),
                Event(
                    id = "event12",
                    name = "Конференция",
                    thumbnail = "https://blog.ostrovok.ru/wp-content/uploads/2022/11/2%D0%BA%D0%BE%D0%BF%D0%B8%D1%8F-15.jpg",
                    description = "Конференция по математике",
                    date = Date(),
                    place = Place(
                        name = "Д431",
                    ),
                    status = null,
                    users = null,
                    isParticipant = false
                ),
                Event(
                    id = "event13",
                    name = "Собрание старост",
                    thumbnail = "https://swsu.ru/upload/iblock/a84/63ta4945szg4pztp3yq8di8mhyo29g3e/MG_8771.jpg",
                    description = "Собрание старост первого курса",
                    date = Date(),
                    place = Place(
                        name = "A341",
                    ),
                    status = null,
                    users = null,
                    isParticipant = true
                ),
            ),
            bookings = null,
            profileImageUrl = "",
            card = "1234567890123456"
        )

        viewModel.profie = profile
        binding.nameTextView.text = viewModel.profie!!.name
        setEventAdapter()

        return root
    }

    private fun setButtonsClickListener()
    {
        binding.profileImage.setOnClickListener {
            selectImageFromGallery()
        }
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            binding.profileImage.setImageURI(imageUri)
        }
    }

    private fun setEventAdapter()
    {
        eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.profie!!.events!!, null, findNavController())
        eventRecyclerView.adapter = eventAdapter
    }

    private fun setOptionAdapter()
    {
        optionsAdapter = OptionsAdapter(viewModel.getOptions(), findNavController())
        optionsRecyclerView.adapter = optionsAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
