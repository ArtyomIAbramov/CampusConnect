package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.app.DatePickerDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.api.models.BookingSlot
import dev.cremenb.campus_connect.databinding.FragmentCreateEventAndCoworkingBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.EdgeItemDecoration
import java.util.Calendar
import java.util.Date


interface SlotSelectionListener {
    fun slotSelected(eventId : Int, slot: BookingSlot)

    fun eventPlaceSelected(eventId : Int)
}

@AndroidEntryPoint
class CreateEventAndCoworkingFragment : Fragment(), SlotSelectionListener  {

    private val viewModel: CreateEventAndCoworkingViewModel by viewModels()

    private var _binding : FragmentCreateEventAndCoworkingBinding? = null

    private val binding get() = _binding!!

    private lateinit var placeRecyclerView: RecyclerView
    private lateinit var eventPlaceRecyclerView: RecyclerView
    private lateinit var placeAdapter: CreateEventAndCoworkingAdapter

    private lateinit var eventName : String
    private lateinit var eventDescription : String
    private lateinit var eventDate : Date
    private var eventTypeId : Int? = null

    override fun slotSelected(eventId : Int, slot: BookingSlot) {
        viewModel.createCoworkong(eventId,slot.from,slot.to)
    }

    override fun eventPlaceSelected(eventId : Int) {
        eventName = binding.eventName.text.toString()
        eventDescription = binding.eventDescription.text.toString()

        viewModel.createEvent(eventId,eventName, eventDescription,eventTypeId!! ,eventDate)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateEventAndCoworkingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonsClickListener()

        placeRecyclerView = binding.coworkingRecyclerView
        placeRecyclerView.addItemDecoration(EdgeItemDecoration(10))
        placeRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        eventPlaceRecyclerView = binding.recyclerView
        eventPlaceRecyclerView.addItemDecoration(EdgeItemDecoration(10))
        eventPlaceRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.placesAndSlotsResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allPlacesAndSlots = result.data
                    setAdapter(1)
                }
                is RequestResult.Error -> {
                }
                is RequestResult.Exception -> {
                }
                is RequestResult.InProgress -> {
                }
            }
        }

        viewModel.eventPlacesResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allEventPlaces = result.data
                    setAdapter(2)
                }
                is RequestResult.Error -> {
                }
                is RequestResult.Exception -> {
                }
                is RequestResult.InProgress -> {
                }
            }
        }

        viewModel.createBookingResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    clearField()
                    Toast.makeText(context, "Вы успешно забронировали!", Toast.LENGTH_SHORT).show()
                }
                is RequestResult.Error -> {
                }
                is RequestResult.Exception -> {
                }
                is RequestResult.InProgress -> {
                }
            }
        }

        viewModel.createEventResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    clearField()
                    Toast.makeText(context, "Мероприятие создано!", Toast.LENGTH_SHORT).show()
                }
                is RequestResult.Error -> {
                }
                is RequestResult.Exception -> {
                }
                is RequestResult.InProgress -> {
                }
            }
        }

        return root
    }
    
    private fun clearField(){
        binding.textEventType.visibility = View.GONE
        binding.eventTypeScroll.visibility = View.GONE
        binding.textEventName.visibility = View.GONE
        binding.eventName.visibility = View.GONE
        binding.eventName.text.clear()
        binding.textEventDescription.visibility = View.GONE
        binding.eventDescription.visibility = View.GONE
        binding.eventDescription.text.clear()
        binding.textEventData.visibility = View.GONE
        binding.buttonDatePicker.visibility = View.GONE
        binding.eventDate.visibility = View.GONE
        binding.eventDate.text.clear()
        placeRecyclerView.visibility =View.GONE
        eventPlaceRecyclerView.visibility = View.GONE
    }

    private fun setAdapter(t : Int)
    {
        if(t == 1)
        {
            placeAdapter = CreateEventAndCoworkingAdapter(requireActivity(), viewModel.allPlacesAndSlots!!, null, this ,1)
            placeRecyclerView.adapter = placeAdapter
            placeRecyclerView.visibility = View.VISIBLE

            val location = IntArray(2)
            placeRecyclerView.getLocationInWindow(location)
            val scrollY = location[1] - binding.scrollView.top

            binding.scrollView.post {
                binding.scrollView.scrollTo(0, scrollY)
            }
        }
        else
        {
            val placeAdapter = CreateEventAndCoworkingAdapter(requireActivity(), null ,viewModel.allEventPlaces!!, this, 2)
            eventPlaceRecyclerView.adapter = placeAdapter
            eventPlaceRecyclerView.visibility = View.VISIBLE

            val location = IntArray(2)
            eventPlaceRecyclerView.getLocationInWindow(location)
            val scrollY = location[1] - binding.scrollView.top

            binding.scrollView.post {
                binding.scrollView.scrollTo(0, scrollY+100)
            }
        }
    }

    private fun setButtonsClickListener()
    {
        binding.buttonEvent.setOnClickListener {
            clearField()
            binding.textEventType.visibility = View.VISIBLE
            binding.eventTypeScroll.visibility = View.VISIBLE
        }

        binding.buttonCoworking.setOnClickListener {
            clearField()
            binding.textCoworkingData.visibility = View.VISIBLE
            binding.buttonDateCoworkingPicker.visibility = View.VISIBLE
            binding.coworkingDate.visibility = View.VISIBLE
        }

        binding.buttonEntertainment.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
            eventTypeId = 2
        }

        binding.buttonStudy.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
            eventTypeId = 1
        }

        binding.buttonSport.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
            eventTypeId = 3
        }

        binding.eventName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (!s.toString().trim().isEmpty()) {
                    binding.textEventDescription.visibility = View.VISIBLE
                    binding.eventDescription.visibility = View.VISIBLE
                }
            }
        })

        binding.eventDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (!s.toString().trim().isEmpty()) {
                    binding.textEventData.visibility = View.VISIBLE
                    binding.buttonDatePicker.visibility = View.VISIBLE
                    binding.eventDate.visibility = View.VISIBLE
                }
            }
        })

        binding.buttonDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
                binding.eventDate.hint = "$dayOfMonth/${monthOfYear + 1}/$year"
                calendar.set(year, monthOfYear, dayOfMonth)
                val date = calendar.time
                eventDate = date
                viewModel.getEventsPlaces(date)

            }, year, month, day)

            dpd.show()
        }

        binding.buttonDateCoworkingPicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
                binding.coworkingDate.hint = "$dayOfMonth/${monthOfYear + 1}/$year"
                calendar.set(year, monthOfYear, dayOfMonth)
                val date = calendar.time
                viewModel.getPlacesAndSlots(date)

            }, year, month, day)

            dpd.show()
        }
    }
}