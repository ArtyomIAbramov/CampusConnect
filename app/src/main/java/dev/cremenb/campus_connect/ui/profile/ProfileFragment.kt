package dev.cremenb.campus_connect.ui.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import dev.cremenb.campus_connect.ui.events.EventCatalogAdapter
import dev.cremenb.data.models.RequestResult

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventCatalogAdapter

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        eventRecyclerView = binding.eventRecyclerView
        eventRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.profie = result.data
                    setAdapter()
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

    private fun setAdapter()
    {
        eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.profie!!.events!!, null)
        eventRecyclerView.adapter = eventAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
