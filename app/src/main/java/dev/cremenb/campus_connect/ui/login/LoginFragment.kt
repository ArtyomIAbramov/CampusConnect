package dev.cremenb.campus_connect.ui.login

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieDrawable
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentLoginBinding
import dev.cremenb.data.models.RequestResult
import java.util.Timer
import kotlin.concurrent.schedule

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
        observeResults()

        val root: View = binding.root
        return root
    }

    private fun setButtonsClickListener()
    {
        binding.loginButton.setOnClickListener {
            //viewModel.login("Arteasdzm63sdf435627", "Artem")
            val login = binding.loginInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.login(login, password)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_login_to_navigation_registration)
        }
    }

    private fun observeResults()
    {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    binding.defaultLoginLayout.visibility=View.GONE
                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()

                    findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                }
                is RequestResult.Error -> {
                    binding.defaultLoginLayout.visibility=View.VISIBLE

                    binding.loginError.visibility = View.VISIBLE
                    binding.loginError.text = "Проверьте логин"

                    binding.passwordError.visibility = View.VISIBLE
                    binding.passwordError.text = "Проверьте пароль"

                    binding.loginInput.setBackgroundResource(R.drawable.rounded_edittext_background_error)
                    binding.passwordInput.setBackgroundResource(R.drawable.rounded_edittext_background_error)

                    Timer().schedule(3000) {
                        activity?.runOnUiThread {
                            binding.loginError.visibility = View.GONE
                            binding.passwordError.visibility = View.GONE

                            binding.loginInput.setBackgroundResource(R.drawable.rounded_edittext_background)
                            binding.passwordInput.setBackgroundResource(R.drawable.rounded_edittext_background)
                        }
                    }

                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.Exception -> {
                    binding.defaultLoginLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility = View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.InProgress -> {}
            }
        }

        viewModel.authenticationResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    binding.defaultLoginLayout.visibility=View.GONE
                    binding.progressAnimation.visibility = View.VISIBLE
                    binding.progressAnimation.playAnimation()
                    binding.progressAnimation.repeatCount = LottieDrawable.INFINITE

                    findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                }
                is RequestResult.Error -> {
                    binding.defaultLoginLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility=View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.Exception -> {
                    binding.defaultLoginLayout.visibility=View.VISIBLE
                    binding.progressAnimation.visibility=View.GONE
                    binding.progressAnimation.cancelAnimation()
                }

                is RequestResult.InProgress -> {
                    binding.defaultLoginLayout.visibility=View.GONE
                    binding.progressAnimation.visibility=View.VISIBLE
                    binding.progressAnimation.playAnimation()
                    binding.progressAnimation.repeatCount = LottieDrawable.INFINITE
                }
            }
        }
    }
}