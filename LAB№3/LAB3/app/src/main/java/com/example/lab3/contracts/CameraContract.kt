package com.example.lab3.contracts;

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

class CameraContract : ActivityResultContract<CameraContract.Input, CameraContract.Output>() {

    data class Input(val uri: Uri)
    data class Output(val result: Boolean)

    override fun createIntent(context: Context, input: Input?): Intent {
        val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        i.putExtra(MediaStore.EXTRA_OUTPUT, input?.uri)
        return i
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Output {
        val result = resultCode == RESULT_OK
        return Output(result)
    }
}