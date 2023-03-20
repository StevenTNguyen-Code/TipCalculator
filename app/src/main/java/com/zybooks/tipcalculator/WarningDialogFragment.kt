package com.zybooks.tipcalculator
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class WarningDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.warning_message)
        builder.setMessage(R.string.BillCost)
        builder.setPositiveButton(R.string.ok_dialogButton, null)
        return builder.create()
    }
}