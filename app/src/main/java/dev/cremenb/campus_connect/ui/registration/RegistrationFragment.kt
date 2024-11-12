package dev.cremenb.campus_connect.ui.registration

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.api.models.University
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentRegistrationBinding
import dev.cremenb.data.models.RequestResult

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding : FragmentRegistrationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    var selectedUniversityId : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupSpinner(getUniversities())

        setButtonsClickListener()

        return root
    }

    private fun getUniversities(): List<University> {

        val list = listOf(
            University(
                id = 0,
                name = "ИГЭУ",
                initials = "ИГЭУ",
                logo = null,
                description = null,
            ),
            University(
                id = 0,
                name = "ИГХТУ",
                initials = "ИГХТУ",
                logo = null,
                description = null,
            ),
            University(
                id = 0,
                name = "ИВГУ",
                initials = "ИВГУ",
                logo = null,
                description = null,
            ),
        )

        return list
    }

    private fun setButtonsClickListener()
    {
        binding.registrationButton.setOnClickListener {
            binding.defaultRegisterLayout.visibility =View.GONE
            findNavController().navigate(R.id.action_navigation_registration_to_navigation_card)
        }
    }

    fun setupSpinner(universities: List<University>) {
        val adapter = ArrayAdapter(requireActivity().applicationContext, android.R.layout.simple_spinner_item, universities.map { it.initials })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = binding.spinnerUniversities
        spinner.adapter = adapter
        spinner.setSelection(1)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedUniversityId = universities[position].id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}