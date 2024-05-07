package dev.cremenb.campus_connect.ui.profile

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentProfileBinding
import dev.cremenb.campus_connect.ui.splash.SplashScreenFragment
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding ? = null
    private val viewModel: ProfileViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewError: TextView = binding.error
        val textViewLoading: TextView = binding.loading
        val textViewNone: TextView = binding.none
        val textViewHello: TextView = binding.hello


        // Создаем экземпляр SplashScreenFragment
        val splashFragment = SplashScreenFragment()
        // Показываем SplashScreenFragment
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, splashFragment)
        transaction.addToBackStack(null)
        transaction.commit()


        viewModel.viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val response = viewModel.repository.getProfile()
                withContext(Dispatchers.Main)
                {
                    when (response){
                        is RequestResult.Success -> {
                            textViewHello.text = response.data.name
                        }
                        is RequestResult.Error -> {
                            textViewError.text = response.message
                        }

                        is RequestResult.Exception -> {
                            textViewNone.text = response.e.message
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