package com.example.lab7

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class BasicDialog(private val title: String, private val text: String): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton("OK", null)
            .create()
    }
}