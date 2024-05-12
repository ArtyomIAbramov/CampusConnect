package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.databinding.FragmentCreateEventAndCoworkingBinding
import java.util.Calendar

@AndroidEntryPoint
class CreateEventAndCoworkingFragment : Fragment() {

    companion object {
        fun newInstance() = CreateEventAndCoworkingFragment()
    }

    private val viewModel: CreateEventAndCoworkingViewModel by viewModels()

    private var _binding : FragmentCreateEventAndCoworkingBinding? = null

    private val binding get() = _binding!!

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

        return root
    }

    private fun setButtonsClickListener()
    {
        binding.buttonDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                    val selectedTime = "$selectedHour:$selectedMinute"

                    binding.eventDate.hint = "$selectedDay/${selectedMonth + 1}/$selectedYear $selectedTime"
                }, hour, minute, true)

                timePickerDialog.show()
            }, year, month, day)

            datePickerDialog.show()
        }
    }
}