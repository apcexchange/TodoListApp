package com.example.todo.ui.deleteallcompleted

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel : DeleteAllCompletedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDialog()

    }

//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
//        AlertDialog.Builder(requireContext())
//            .setTitle("Confirm Deletion")
//            .setMessage("Are you Sure you want to Delete All completed Task?" +
//                    "you won't be able to recover them after deletion.")
//            .setNegativeButton("Cancel", null)
//            .setPositiveButton("Delete") { _, _, ->
//                //call viewModel
//                viewModel.onConfirmedClick()
//            }
//            .create()

    private fun showDialog() {
        val builder =  android.app.AlertDialog.Builder(context)
        builder.setTitle("Exit Alert")
        builder.setMessage("Are you sure you want exit the App?")
        builder.setPositiveButton("Exit", { dialogInterface, i -> viewModel.onConfirmedClick() })
        builder.setNegativeButton("cancel", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.cancel() })
        builder.show()

    }
}