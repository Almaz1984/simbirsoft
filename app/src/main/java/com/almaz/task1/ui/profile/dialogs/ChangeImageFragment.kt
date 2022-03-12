package com.almaz.task1.ui.profile.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.almaz.task1.R

class ChangeImageFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_change_image, null)
            val listener = parentFragment as ProfileImageDialogClickListener

            builder.setView(dialogView)
            dialogView.findViewById<LinearLayout>(R.id.take_photo)
                .setOnClickListener {
                    listener.takePhoto()
                    dismiss()
                }
            dialogView.findViewById<LinearLayout>(R.id.delete)
                .setOnClickListener {
                    listener.deletePhoto()
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
