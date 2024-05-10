package dev.cremenb.campus_connect.ui.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import dev.cremenb.data.models.RequestResult


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val viewModel: ProfileViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProfile()
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewHello: TextView = binding.hello

        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    textViewHello.text = result.data.toString()
                }
                is RequestResult.Error -> {
                }

                is RequestResult.Exception -> {
                }

                is RequestResult.InProgress -> {}
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}