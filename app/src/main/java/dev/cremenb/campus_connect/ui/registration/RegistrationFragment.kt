package dev.cremenb.campus_connect.ui.registration

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.api.models.Profile
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentRegistrationBinding
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private var _binding : FragmentRegistrationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewError: TextView = binding.error
        val textViewNone: TextView = binding.none

        val textViewHello: TextView = binding.hello
        val profile = Profile(null, "Artem2","Artem","Artem6",2 ,"Artem","Artem",null,"Artem")
        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val response = viewModel.repository.register(profile)
                withContext(Dispatchers.Main)
                {
                    when (response){
                        is RequestResult.Success -> {
                            textViewHello.text = "horosho"
                        }
                        is RequestResult.Error -> {
                            textViewError.text = "ploho"
                        }

                        is RequestResult.Exception -> {
                            textViewNone.text = "ploho"
                        }
                    }
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}