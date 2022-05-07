package com.example.lab6

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class Dialog(private val number: Int): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Pressed number")
            .setMessage("You have pressed number $number")
            .setPositiveButton("OK", null)
            .create()

    }
}