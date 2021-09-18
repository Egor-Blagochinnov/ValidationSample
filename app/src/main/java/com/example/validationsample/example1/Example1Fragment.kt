package com.example.validationsample.example1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.egorblagochinnov.validators.Condition
import com.egorblagochinnov.validators.ValidationResult
import com.egorblagochinnov.validators.validateBy
import com.egorblagochinnov.validators.viewbinders.LiveDataValidatorViewBinder
import com.example.validationsample.R
import com.example.validationsample.databinding.Example1FragmentBinding
import java.lang.ref.WeakReference

class Example1Fragment : Fragment() {
    private lateinit var binding: Example1FragmentBinding

    private lateinit var viewModel: Example1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.example_1_fragment, container, false)
        binding = Example1FragmentBinding.bind(view)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(Example1ViewModel::class.java)
        binding.viewModel = viewModel

        with(binding.editText1) {
            validateBy(viewLifecycleOwner, viewModel.textField1Validator)
        }

        with(binding.editText2) {
            validateBy(viewLifecycleOwner, viewModel.textField2Validator)
        }

        object : LiveDataValidatorViewBinder<TextView, String?>(WeakReference(binding.editText1), viewModel.textField1Validator) {
            override fun onValidationResult(view: TextView?, result: ValidationResult?) {

            }

            override fun onConditionsChanged(conditions: Set<Condition<String?>>) {

            }

            override fun onOperatorChanged() {

            }
        }

        viewModel.muxValidator.observe(viewLifecycleOwner) {
            if (it.isValid) {
                binding.state.text = "Correct!"
                binding.state.setTextColor(ContextCompat.getColor(requireContext(), R.color.state_success))
            } else {
                binding.state.text = it.errorMessage ?: "Error message is null"
                binding.state.setTextColor(ContextCompat.getColor(requireContext(), R.color.design_default_color_error))
            }

        }
    }

    companion object {
        fun newInstance() = Example1Fragment()
    }
}