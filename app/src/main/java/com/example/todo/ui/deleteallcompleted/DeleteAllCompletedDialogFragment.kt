package com.example.todo.ui.deleteallcompleted

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel : DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm Deletion")
            .setMessage("Are you Sure you want to Deelete All completed Task?" +
                    "you won't be able to recover them afterb deletion.")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Delete") { _, _, ->
                //call viewModel
                viewModel.onConfirmedClick()
            }
            .create()
}