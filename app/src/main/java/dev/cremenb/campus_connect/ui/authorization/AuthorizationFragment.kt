package dev.cremenb.campus_connect.ui.authorization

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
import dev.cremenb.campus_connect.databinding.FragmentAuthorizationBinding
import dev.cremenb.campus_connect.databinding.FragmentRegistrationBinding
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
    private var _binding : FragmentAuthorizationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewError: TextView = binding.error
        val textViewNone: TextView = binding.none

        val textViewHello: TextView = binding.hello
        val profile = Profile(null, "Artem2","Artem","Artem6",2 ,"Artem","Artem",null,"Artem")
        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val response = viewModel.repository.isAuthenticated()
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
}