package dev.cremenb.campus_connect.ui.registration

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.databinding.FragmentRegistrationBinding
import dev.cremenb.data.models.RequestResult

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

        viewModel.getUniversities()

        setButtonsClickListener()
        observeRegistrationResult()

        return root
    }

    private fun setButtonsClickListener()
    {
        binding.registrationButton.setOnClickListener {
            val name = binding.registrationNameInput.text.toString()
            val surname = binding.registrationSurnameInput.text.toString()
            val login = binding.registrationLoginInput.text.toString()
            val gender = if (binding.maleRadioButton.isChecked) 1 else 2
            val password = binding.registrationPasswordInput.text.toString()
            // Код для получения университета из элемента интерфейса
            viewModel.register(name, surname, login, gender, password, 1)
        }
    }

    private fun observeRegistrationResult()
    {
        viewModel.registrationResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {
                    binding.defaultRegisterLayout.visibility=View.GONE
                    binding.registrationButton.text = result.data.toString()
                    findNavController().navigate(R.id.action_navigation_registration_to_navigation_home)
                }
                is RequestResult.Error -> {
                    // Обработка ошибки
                }
                is RequestResult.Exception -> {
                    // Обработка исключения
                }
                is RequestResult.InProgress -> {
                    // Обработка состояния в процессе
                }
                // Другие возможные состояния
            }
        }

        viewModel.universitiesResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is RequestResult.Success -> {

                }
                is RequestResult.Error -> {
                    // Обработка ошибки
                }
                is RequestResult.Exception -> {
                    // Обработка исключения
                }
                is RequestResult.InProgress -> {
                    // Обработка состояния в процессе
                }
                // Другие возможные состояния
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}