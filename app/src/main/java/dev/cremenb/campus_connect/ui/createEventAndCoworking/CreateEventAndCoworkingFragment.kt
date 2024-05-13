package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.databinding.FragmentCreateEventAndCoworkingBinding
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.EdgeItemDecoration
import java.util.Calendar

@AndroidEntryPoint
class CreateEventAndCoworkingFragment : Fragment() {

    companion object {
        fun newInstance() = CreateEventAndCoworkingFragment()
    }

    private val viewModel: CreateEventAndCoworkingViewModel by viewModels()

    private var _binding : FragmentCreateEventAndCoworkingBinding? = null

    private val binding get() = _binding!!

    private lateinit var placeRecyclerView: RecyclerView
    private lateinit var placeAdapter: CreateEventAndCoworkingAdapter
    private lateinit var selectedTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateEventAndCoworkingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setButtonsClickListener()

        placeRecyclerView = binding.recyclerView
        placeRecyclerView.addItemDecoration(EdgeItemDecoration(10))
        placeRecyclerView.layoutManager = GridLayoutManager(activity, 2)

        viewModel.placesResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.allPlaces = result.data
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
        placeAdapter = CreateEventAndCoworkingAdapter(requireActivity(), viewModel.allPlaces!!)
        placeRecyclerView.adapter = placeAdapter
    }

    private fun setButtonsClickListener()
    {
        binding.buttonEvent.setOnClickListener {
            binding.textEventType.visibility = View.VISIBLE
            binding.eventTypeScroll.visibility = View.VISIBLE
        }

        binding.buttonEntertainment.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
        }

        binding.buttonStudy.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
        }

        binding.buttonSport.setOnClickListener {
            binding.textEventName.visibility = View.VISIBLE
            binding.eventName.visibility = View.VISIBLE
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
                viewModel.getPlaces()
            }, year, month, day)

            dpd.show()
        }
    }
}