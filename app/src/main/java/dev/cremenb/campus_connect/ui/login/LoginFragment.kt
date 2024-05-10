package dev.cremenb.campus_connect.ui.login

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentLoginBinding
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding : FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.checkAuthentication()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setButtonsClickListener()
        observeRegistrationResult()

        val root: View = binding.root
        return root
    }

    private fun setButtonsClickListener()
    {
        binding.button2.setOnClickListener {
            viewModel.login("Artem2", "Artem")
        }

        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_login_to_navigation_registration)
        }
    }

    private fun observeRegistrationResult()
    {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    binding.defaultLayout.visibility=View.GONE
                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()

                    findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                }
                is RequestResult.Error -> {
                    binding.defaultLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.Exception -> {
                    binding.defaultLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.InProgress -> {}
            }
        }

        viewModel.authenticationResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    binding.defaultLayout.visibility=View.GONE
                    binding.progressAnimation.visibility = View.VISIBLE
                    binding.progressAnimation.playAnimation()
                    binding.progressAnimation.repeatCount = LottieDrawable.INFINITE

                    findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                }
                is RequestResult.Error -> {
                    binding.defaultLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility=View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.Exception -> {
                    binding.defaultLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility=View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.InProgress -> {
                    binding.defaultLayout.visibility=View.GONE
                    binding.progressAnimation.visibility=View.VISIBLE
                    binding.progressAnimation.playAnimation()
                    binding.progressAnimation.repeatCount = LottieDrawable.INFINITE
                }
            }
        }
    }
}