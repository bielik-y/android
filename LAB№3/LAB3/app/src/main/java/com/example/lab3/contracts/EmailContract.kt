package com.example.lab3.contracts

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class EmailContract: ActivityResultContract<EmailContract.Input, EmailContract.Output>() {
    data class Input(val subject: String, val uri: Uri)
    data class Output(val result: Boolean)

    override fun createIntent(context: Context, input: Input?): Intent {
        val i = Intent(Intent.ACTION_SEND);
        //i.putExtra(Intent.EXTRA_EMAIL, Array<String>(1){"******@gmail.com"}) //i can use this line if i wanna set email address automatically
        i.putExtra(Intent.EXTRA_SUBJECT, input?.subject)
        i.setType("application/image")
        i.putExtra(Intent.EXTRA_STREAM, input?.uri);
        return i
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Output {
        val result = resultCode == RESULT_OK //email intent always return 0 as soon as sending starts or sending is canceled
        return Output(result)
        }
    }