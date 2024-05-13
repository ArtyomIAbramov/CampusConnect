package dev.cremenb.campus_connect.ui.card

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.databinding.FragmentCardBinding

@AndroidEntryPoint
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
        return root
    }
}