package dev.cremenb.campus_connect.ui.card

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.MainActivity
import dev.cremenb.campus_connect.databinding.FragmentCardBinding

@AndroidEntryPoint
class CardFragment : Fragment() {

    private var _binding : FragmentCardBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)

        (activity as MainActivity).navView.visibility = View.VISIBLE
        val root: View = binding.root

        return root
    }
}