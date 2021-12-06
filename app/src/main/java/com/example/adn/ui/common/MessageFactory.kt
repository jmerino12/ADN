package com.example.adn.ui.common

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MessageFactory {
    companion object {
        const val TYPE_ERROR = "typeError"
        const val TYPE_INFO = "typeInfo"
    }

    fun getDialog(context: Context, type: String, message: String): MaterialAlertDialogBuilder {
        when (type) {
            TYPE_ERROR -> {
                return MaterialAlertDialogBuilder(context)
                    .setMessage(message)
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }

            }
            TYPE_INFO -> {
                return MaterialAlertDialogBuilder(context)
                    .setMessage(message)
            }
        }
        return MaterialAlertDialogBuilder(context)
            .setMessage("Necesitas añadir el nuevo tipo")
    }
}