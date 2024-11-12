package dev.cremenb.campus_connect.ui.login

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        checkAuthentication()
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setButtonsClickListener()

        val root: View = binding.root
        return root
    }

    private fun checkAuthentication() {
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val isLoggedInFromPrefs = sharedPreferences.getBoolean("isLoggedIn", false)

        if(isLoggedInFromPrefs) {
            binding.defaultLoginLayout.visibility=View.GONE

            findNavController().navigate(R.id.action_navigation_login_to_navigation_card)
        }

    }

    private fun setButtonsClickListener()
    {
        binding.loginButton.setOnClickListener {
            val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", true)
            editor.apply()

            binding.defaultLoginLayout.visibility=View.GONE

            findNavController().navigate(R.id.action_navigation_login_to_navigation_card)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_login_to_navigation_registration)
        }
    }
}