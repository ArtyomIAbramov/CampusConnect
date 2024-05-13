package dev.cremenb.campus_connect.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import dev.cremenb.campus_connect.ui.events.EventCatalogAdapter
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.HorizontalSpaceItemDecoration
import dev.cremenb.utilities.VerticalSpaceItemDecoration

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventCatalogAdapter

    private lateinit var optionsRecyclerView: RecyclerView
    private lateinit var optionsAdapter: OptionsAdapter

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 100
    }

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

        setButtonsClickListener()

        eventRecyclerView = binding.eventRecyclerView
        eventRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(30))
        eventRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        optionsRecyclerView = binding.optionsRecyclerView
        optionsRecyclerView.addItemDecoration(VerticalSpaceItemDecoration(30))
        optionsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        setOptionAdapter()

        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    viewModel.profie = result.data
                    binding.nameTextView.text = viewModel.profie!!.login
                    setEventAdapter()
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

    private fun setButtonsClickListener()
    {
        binding.profileImage.setOnClickListener {
            selectImageFromGallery()
        }
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            binding.profileImage.setImageURI(imageUri)
        }
    }

    private fun setEventAdapter()
    {
        eventAdapter = EventCatalogAdapter(requireActivity(), viewModel.profie!!.events!!, null, findNavController())
        eventRecyclerView.adapter = eventAdapter
    }

    private fun setOptionAdapter()
    {
        optionsAdapter = OptionsAdapter(requireActivity(), viewModel.getOptions())
        optionsRecyclerView.adapter = optionsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
