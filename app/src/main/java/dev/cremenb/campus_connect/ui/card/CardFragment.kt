package dev.cremenb.campus_connect.ui.card

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentCardBinding
import dev.cremenb.campus_connect.ui.events.EventsFragment

class CardFragment : Fragment() {

    companion object {
        fun newInstance() = CardFragment()
    }
    private var _binding : FragmentCardBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button : Button = binding.button

        button.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_card_to_navigation_events)
        }
        return root
    }
}