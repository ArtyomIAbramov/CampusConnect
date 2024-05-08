package dev.cremenb.campus_connect.ui.events

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentCardBinding
import dev.cremenb.campus_connect.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    companion object {
        fun newInstance() = EventsFragment()
    }
    private var _binding : FragmentEventsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: EventsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val button : Button = binding.button

        button.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_events_to_navigation_card)
        }
        return root
    }
}