package com.example.todo.ui.addedittask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddAndEditTaskBinding
import com.example.todo.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddAndEditTaskFragment : Fragment(R.layout.fragment_add_and_edit_task) {

private val viewModel: AddEditTaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAddAndEditTaskBinding.bind(view)

        binding.apply {
            editTextAddTask.setText(viewModel.taskName)
            checkboxImportantTask.isChecked = viewModel.taskImportance
            checkboxImportantTask.jumpDrawablesToCurrentState()
            dateCreated.isVisible = viewModel.task != null
            dateCreated.text = "created ${viewModel.task?.createdDateFormat}"

            editTextAddTask.addTextChangedListener {
                viewModel.taskName = it.toString()
            }
            checkboxImportantTask.setOnCheckedChangeListener { _, isChecked ->
                viewModel.taskImportance = isChecked
            }
            fabSaveTask.setOnClickListener {
                viewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditTaskEvent.collect { event ->
                when(event){
                    is AddEditTaskViewModel.AddEditTaskEvent.NavigateBackWithResult -> {
                        binding.editTextAddTask.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }

                    is AddEditTaskViewModel.AddEditTaskEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(),event.message,Snackbar.LENGTH_LONG).show()

                    }
                }.exhaustive
            }
        }
    }
}
